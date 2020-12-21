package com.example.wanandroid_vpa.discover.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.base.BaseHolder
import com.example.wanandroid_vpa.discover.bean.NavigationIndexBean
import com.example.wanandroid_vpa.discover.bean.TreeIndexBean

/**
 * Created by geegumb on 2020/12/8
 *
 */
class DiscoverIndexAdapter<T>(private val mCallBack: IndexClickCallBack<T>) :
    RecyclerView.Adapter<BaseHolder<T>>() {

    private val mDatas = arrayListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_rcv_structure_index,
            parent, false
        )
        return DiscoverIndexHolder(view, mCallBack)
    }

    override fun onBindViewHolder(holder: BaseHolder<T>, position: Int) {
        holder.bind(mDatas[position])
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }

    fun add(data: List<T>?) {
        data?.let {
            mDatas.addAll(data)
            notifyDataSetChanged()
        }
    }

    fun updateItemSelected(t: T) {
        mDatas.forEach {
            if (t != it) {
                if (it is TreeIndexBean) it.isSelected = false
                if (it is NavigationIndexBean) it.isSelected = false
            }
        }
        notifyDataSetChanged()
    }

}

class DiscoverIndexHolder<T>(v: View, private val callBack: IndexClickCallBack<T>) :
    BaseHolder<T>(v) {
    override fun bind(t: T) {
        when(t) {
            is TreeIndexBean -> {
                (itemView as TextView).text = t.name
                itemView.isSelected = t.isSelected
                itemView.setOnClickListener {
                    t.isSelected = !t.isSelected
                    itemView.isSelected = t.isSelected
                    callBack.onIndexClick(t)
                }
            }
            is NavigationIndexBean -> {
                (itemView as TextView).text = t.name
                itemView.isSelected = t.isSelected
                itemView.setOnClickListener {
                    t.isSelected = !t.isSelected
                    itemView.isSelected = t.isSelected
                    callBack.onIndexClick(t)
                }
            }
        }
    }
}

interface IndexClickCallBack<T> {
    fun onIndexClick(bean: T)
}