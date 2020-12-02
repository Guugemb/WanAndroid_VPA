package com.example.wanandroid_vpa.home.holder

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.base.BaseHolder
import com.example.wanandroid_vpa.home.adapter.HomeBannerAdapter
import com.example.wanandroid_vpa.home.bean.BannerBeanWrapper.BannerBean

/**
 * Created by geegumb on 2020/11/30
 *
 */
class HomeBannerHolder(v: View): BaseHolder<Any>(v) {

    private val mBannerAdapter = HomeBannerAdapter()

    init {
        v.findViewById<ViewPager2>(R.id.vpBanner).adapter = mBannerAdapter
    }
    override fun bind(t: Any) {
        if (t is List<*>) {
            mBannerAdapter.addData(t as List<BannerBean>)
        }
    }
}