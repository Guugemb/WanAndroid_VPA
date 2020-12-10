package com.example.wanandroid_vpa.home.holder

import android.content.Intent
import android.view.View
import android.widget.TextView
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.base.BaseHolder
import com.example.wanandroid_vpa.home.bean.ArticleBean
import com.example.wanandroid_vpa.network.WebActivity

/**
 * Created by geegumb on 2020/11/30
 *
 */
class HomeArticleItemHolder(v: View) : BaseHolder<Any>(v) {

    override fun bind(t: Any) {
        if (t !is ArticleBean) return
        itemView.let{
            it.findViewById<TextView>(R.id.tvTime).text = t.niceDate
            it.findViewById<TextView>(R.id.tvTitle).text = t.title
            it.findViewById<TextView>(R.id.tvAuthor).text = if (t.author.isNullOrEmpty()) {
                t.shareUser
            } else {
                t.author
            }
            it.findViewById<TextView>(R.id.tvCategory).text = t.superChapterName
            it.setOnClickListener { view ->
                val intent = Intent(view.context, WebActivity::class.java)
                intent.putExtra(WebActivity.FLAG_WEB_TITLE, t.title)
                intent.putExtra(WebActivity.FLAG_WEB_LINK, t.link)
                view.context.startActivity(intent)
            }
        }
    }
}