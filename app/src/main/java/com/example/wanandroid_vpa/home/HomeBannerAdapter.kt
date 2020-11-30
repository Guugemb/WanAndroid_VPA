package com.example.wanandroid_vpa.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.home.BannerBeanWrapper.BannerBean

/**
 * Created by geegumb on 2020/11/30
 *
 */
class HomeBannerAdapter: PagerAdapter() {

    private val mDataList = arrayListOf<BannerBean>()

    override fun getCount(): Int {
        return mDataList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val iv = LayoutInflater.from(container.context).inflate(R.layout.item_banner_home,
            container, false)
        Glide.with(iv).load(mDataList[position].imagePath).into(iv as ImageView)
        return iv
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeViewAt(position)
    }

    fun addData(list: List<BannerBean>) {
        mDataList.addAll(list)
        notifyDataSetChanged()
    }
}