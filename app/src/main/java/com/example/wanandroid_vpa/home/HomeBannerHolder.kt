package com.example.wanandroid_vpa.home

import android.view.View
import androidx.viewpager.widget.ViewPager
import com.example.wanandroid_vpa.base.BaseHolder
import com.example.wanandroid_vpa.home.BannerBeanWrapper.BannerBean
import com.example.wanandroid_vpa.home.HomeRvAdapter.Wrapper

/**
 * Created by geegumb on 2020/11/30
 *
 */
class HomeBannerHolder(v: View): BaseHolder<Any>(v) {

    private val mBannerAdapter = HomeBannerAdapter()

    init {
        (v as ViewPager).adapter = mBannerAdapter
    }
    override fun bind(t: Any) {
        if (t is List<*>) {
            mBannerAdapter.addData(t as List<BannerBean>)
        }
    }
}