package com.example.wanandroid_vpa.home.adapter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.bumptech.glide.Glide
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.base.BaseFragment
import com.example.wanandroid_vpa.home.adapter.HomeBannerAdapter2.BannerFragment.Companion.BANNER_BEAN
import com.example.wanandroid_vpa.home.bean.BannerJsonWrapper.BannerBean
import com.example.wanandroid_vpa.network.WebActivity

/**
 * Created by geegumb on 2020/12/2
 *
 */
class HomeBannerAdapter2(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {

    private val mDataList = arrayListOf<BannerBean>()

    override fun getCount(): Int {
        return mDataList.size
    }

    override fun getItem(position: Int): Fragment {
        val fragment = BannerFragment.newInstance()
        val bundle = Bundle()
        bundle.putSerializable(BANNER_BEAN, mDataList[position])
        fragment.arguments = bundle
        return fragment
    }

    fun addData(beans: List<BannerBean>?) {
        beans?.let {
            mDataList.addAll(beans)
            notifyDataSetChanged()
        }
    }

    internal class BannerFragment : BaseFragment() {

        override fun getLayoutRes(): Int {
            return R.layout.item_banner_home
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val bannerBean = arguments?.get(BANNER_BEAN) as BannerBean
            Glide.with(view).load(bannerBean.imagePath).into(view as ImageView)
            view.setOnClickListener {
                val intent = Intent(view.context, WebActivity::class.java)
                intent.putExtra(WebActivity.FLAG_WEB_TITLE, bannerBean.title)
                intent.putExtra(WebActivity.FLAG_WEB_LINK, bannerBean.url)
                view.context.startActivity(intent)
            }
        }

        companion object {
            fun newInstance() = BannerFragment()
            const val BANNER_BEAN = "banner_bean"
        }
    }
}