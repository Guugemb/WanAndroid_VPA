package com.example.wanandroid_vpa.discover.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.base.BaseHolder
import com.example.wanandroid_vpa.discover.bean.TreeItemBean
import com.example.wanandroid_vpa.discover.activity.TreeItemDetailActivity

/**
 * Created by geegumb on 2020/12/8
 *
 */
class TreeItemAdapter : RecyclerView.Adapter<TreeItemHolder>() {

    private val mDatas = arrayListOf<TreeItemBean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreeItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_tree, parent, false)
        return TreeItemHolder(view)
    }

    override fun onBindViewHolder(holder: TreeItemHolder, position: Int) {
        holder.bind(mDatas[position])
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }

    fun add(list: List<TreeItemBean>?) {
        list?.let {
            mDatas.addAll(list)
            notifyDataSetChanged()
        }
    }

    fun clear() = mDatas.clear()
}

class TreeItemHolder(v: View) : BaseHolder<TreeItemBean>(v) {
    override fun bind(t: TreeItemBean) {
        (itemView as TextView).text = t.name
        itemView.setOnClickListener {
            val intent = Intent(itemView.context, TreeItemDetailActivity::class.java)
            intent.putExtra(TreeItemDetailActivity.FLAG_TITLE, t.name)
            intent.putExtra(TreeItemDetailActivity.FLAG_CID, t.id)
            itemView.context.startActivity(intent)
        }
    }
}