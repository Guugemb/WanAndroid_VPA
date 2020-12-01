package com.example.wanandroid_vpa.home.holder

import android.view.View
import android.widget.TextView
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.base.BaseHolder
import com.example.wanandroid_vpa.home.bean.ArticleBean
import kotlinx.android.synthetic.main.item_home_article_rcv.view.*

/**
 * Created by geegumb on 2020/11/30
 *
 */
class HomeArticleItemHolder(v: View) : BaseHolder<Any>(v) {

    private val mView = v

    override fun bind(t: Any) {
        if (t is ArticleBean) {
            mView.findViewById<TextView>(R.id.tvTime).text = t.niceShareDate
            mView.findViewById<TextView>(R.id.tvTitle).text = t.title
            mView.findViewById<TextView>(R.id.tvAuthor).text = t.author
            mView.findViewById<TextView>(R.id.tvCategory).text = t.superChapterName
        }
    }
}