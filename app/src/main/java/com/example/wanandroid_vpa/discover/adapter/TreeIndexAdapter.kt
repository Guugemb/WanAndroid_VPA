package com.example.wanandroid_vpa.discover.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.base.BaseHolder
import com.example.wanandroid_vpa.discover.bean.TreeIndexBean

/**
 * Created by geegumb on 2020/12/8
 *
 */
class TreeIndexAdapter(private val mCallBack: IndexClickCallBack) :
    RecyclerView.Adapter<StructureIndexHolder>() {

    private val mDatas = arrayListOf<TreeIndexBean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StructureIndexHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_rcv_structure_index,
            parent, false
        )
        return StructureIndexHolder(view, mCallBack)
    }

    override fun onBindViewHolder(holder: StructureIndexHolder, position: Int) {
        holder.bind(mDatas[position])
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }

    fun add(data: List<TreeIndexBean>?) {
        data?.let {
            mDatas.addAll(data)
            notifyDataSetChanged()
        }
    }

    fun updateItemSelected(selectedBean: TreeIndexBean) {
        mDatas.forEach {
            if (selectedBean != it) it.isSelected = false
        }
        notifyDataSetChanged()
    }

}

class StructureIndexHolder(v: View, private val callBack: IndexClickCallBack) :
    BaseHolder<TreeIndexBean>(v) {
    override fun bind(t: TreeIndexBean) {
        (itemView as TextView).text = t.name
        itemView.isSelected = t.isSelected
        itemView.setOnClickListener {
            t.isSelected = !t.isSelected
            itemView.isSelected = t.isSelected
            callBack.onIndexClick(t)
        }
    }
}

interface IndexClickCallBack {
    fun onIndexClick(bean: TreeIndexBean)
}