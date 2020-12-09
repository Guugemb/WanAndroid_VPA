package com.example.wanandroid_vpa.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.base.BaseAdapter
import com.example.wanandroid_vpa.base.BaseHolder
import com.example.wanandroid_vpa.home.bean.ArticleBean
import com.example.wanandroid_vpa.home.bean.BannerBeanWrapper.BannerBean
import com.example.wanandroid_vpa.home.holder.HomeArticleItemHolder
import com.example.wanandroid_vpa.home.holder.HomeBannerHolder
import com.example.wanandroid_vpa.home.holder.HomeFooterHolder
import java.lang.IllegalStateException
import java.util.*

/**
 * Created by geegumb on 2020/11/30
 *
 */
class SingleListAdapter : BaseAdapter<Any>() {

    private val mDataList = LinkedList<Wrapper>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<Any> {
        return when (viewType) {
            TYPE_BANNER -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_rv_vp_home,
                    parent, false
                )
                HomeBannerHolder(view)
            }
            TYPE_ITEM_ARTICLE_LIST -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_home_article_rcv,
                    parent, false
                )
                HomeArticleItemHolder(view)
            }
            TYPE_FOOTER_LOADING -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_footer_loading,
                    parent, false
                )
                HomeFooterHolder(view)
            }
            else -> throw IllegalStateException("unsupported view type")
        }
    }

    override fun onBindViewHolder(holder: BaseHolder<Any>, position: Int) {
        holder.bind(mDataList[position].data)
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return mDataList[position].type
    }

    fun addBanners(banners: List<BannerBean>?) {
        banners?.let {
            synchronized(mDataList) { mDataList.add(0, Wrapper(it, TYPE_BANNER)) }
            notifyDataSetChanged()
        }
    }

    fun addArticles(articleList: List<ArticleBean>?) {
        synchronized(mDataList) {
            if (articleList == null || articleList.isEmpty()) return
            val footer = if (mDataList.size > 0 &&
                getItemViewType(mDataList.size - 1) == TYPE_FOOTER_LOADING
            ) {
                mDataList.removeLast()
            } else {
                Wrapper(Any(), TYPE_FOOTER_LOADING)
            }
            articleList.forEach {
                mDataList.add(Wrapper(it, TYPE_ITEM_ARTICLE_LIST))
            }
            mDataList.add(footer)
        }
        notifyDataSetChanged()
    }

    private fun removeBanners() {
        if (mDataList.size > 0 && mDataList[0].data is List<*>) mDataList.removeAt(0)
    }

    private fun removeArticles() {
        var saved: Any? = null
        if (mDataList.size > 0 && mDataList[0].data is List<*>) saved = mDataList[0]
        mDataList.clear()
        saved?.let { mDataList.add(Wrapper(it, TYPE_BANNER)) }
    }

    internal data class Wrapper(var data: Any, var type: Int)

    companion object {
        const val TYPE_BANNER = 0
        const val TYPE_ITEM_ARTICLE_LIST = 1
        const val TYPE_FOOTER_LOADING = 2
    }

    override fun clear() {
        removeBanners()
        removeArticles()
    }

}