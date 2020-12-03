package com.example.wanandroid_vpa.home.holder

import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.base.BaseHolder
import com.example.wanandroid_vpa.home.adapter.HomeBannerAdapter2
import com.example.wanandroid_vpa.home.bean.BannerBeanWrapper.BannerBean

/**
 * Created by geegumb on 2020/11/30
 *
 */
class HomeBannerHolder(v: View): BaseHolder<Any>(v) {

    private val mBannerAdapter = HomeBannerAdapter2((v.context as FragmentActivity)
        .supportFragmentManager)

    init {
        v.findViewById<ViewPager>(R.id.vpBanner).adapter = mBannerAdapter
    }
    override fun bind(t: Any) {
        if (t is List<*>) {
            mBannerAdapter.addData(t as List<BannerBean>)
        }
    }
}