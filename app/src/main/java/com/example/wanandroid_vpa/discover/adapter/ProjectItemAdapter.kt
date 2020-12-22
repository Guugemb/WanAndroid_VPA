package com.example.wanandroid_vpa.discover.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.base.BaseHolder
import com.example.wanandroid_vpa.discover.bean.ProjectItemBean
import com.example.wanandroid_vpa.network.WebActivity

/**
 * Created by geegumb on 2020/12/22
 *
 */
class ProjectItemAdapter : RecyclerView.Adapter<ProjectItemHolder>() {

    private val mData = arrayListOf<ProjectItemBean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectItemHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_project_list, parent, false)
        return ProjectItemHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectItemHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun add(list: List<ProjectItemBean>?) {
        list?.let { mData.addAll(it) }
        notifyDataSetChanged()
    }

    fun clear() {
        mData.clear()
    }
}

class ProjectItemHolder(v: View) : BaseHolder<ProjectItemBean>(v) {

    private val tvTitle = v.findViewById<TextView>(R.id.tvTitle)
    private val tvDesc = v.findViewById<TextView>(R.id.tvDesc)
    private val tvAuthor = v.findViewById<TextView>(R.id.tvAuthor)
    private val tvTime = v.findViewById<TextView>(R.id.tvTime)
    private val ivCover = v.findViewById<ImageView>(R.id.ivCover)

    override fun bind(t: ProjectItemBean) {
        tvTitle.text = t.title
        tvDesc.text = t.desc
        tvAuthor.text = t.author
        tvTime.text = t.niceDate
        Glide.with(ivCover).load(t.envelopePic).into(ivCover)
        itemView.setOnClickListener {
            val intent = Intent(itemView.context, WebActivity::class.java)
            intent.putExtra(WebActivity.FLAG_WEB_LINK, t.link)
                .putExtra(WebActivity.FLAG_WEB_TITLE, t.title)
            itemView.context.startActivity(intent)
        }
    }

}