package com.example.wanandroid_vpa.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.base.BaseHolder
import com.example.wanandroid_vpa.home.bean.ArticleBean
import com.example.wanandroid_vpa.home.bean.BannerBeanWrapper.BannerBean
import com.example.wanandroid_vpa.home.holder.HomeArticleItemHolder
import com.example.wanandroid_vpa.home.holder.HomeBannerHolder
import java.lang.IllegalStateException

/**
 * Created by geegumb on 2020/11/30
 *
 */
class HomeRvAdapter: RecyclerView.Adapter<BaseHolder<Any>>() {

    private val mDataList = arrayListOf<Wrapper>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<Any> {
        return when (viewType) {
            TYPE_BANNER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_vp_home,
                    parent, false)
                HomeBannerHolder(view)
            }
            TYPE_ITEM_RECYCLER_VIEW -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_article_rcv,
                    parent, false)
                HomeArticleItemHolder(view)
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
            mDataList.add(0, Wrapper(it, TYPE_BANNER))
            notifyDataSetChanged()
        }
    }

    fun addArticles(articleList: List<ArticleBean>?) {
        articleList?.forEach {
            mDataList.add(Wrapper(it, TYPE_ITEM_RECYCLER_VIEW))
        }.also { notifyDataSetChanged()}
    }

    internal data class Wrapper(var data: Any, var type: Int)

    companion object {
        const val TYPE_BANNER = 0
        const val TYPE_ITEM_RECYCLER_VIEW = 1
    }

}