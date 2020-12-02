package com.example.wanandroid_vpa.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.base.BaseHolder
import com.example.wanandroid_vpa.home.bean.BannerBeanWrapper.BannerBean
import com.example.wanandroid_vpa.home.adapter.HomeBannerAdapter.BannerHolder
import com.example.wanandroid_vpa.util.ScreenUtils

/**
 * Created by geegumb on 2020/12/1
 *
 */
class HomeBannerAdapter : RecyclerView.Adapter<BannerHolder>() {
    private val mBannerBeans = arrayListOf<BannerBean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_banner_home, parent, false
        )
        (view as ImageView).maxWidth = ScreenUtils.getScreenWidth(view.context)
        return BannerHolder(view)
    }

    override fun onBindViewHolder(holder: BannerHolder, position: Int) {
        holder.bind(mBannerBeans[position])
    }

    override fun getItemCount(): Int {
        return mBannerBeans.size
    }

    fun addData(data: List<BannerBean>?) {
        data?.let {
            mBannerBeans.addAll(data)
            notifyDataSetChanged()
        }
    }

    class BannerHolder(v: View) : BaseHolder<BannerBean>(v) {
        var mView: View = v

        override fun bind(t: BannerBean) {
            Glide.with(mView).load(t.imagePath).into(mView as ImageView)
        }
    }
}