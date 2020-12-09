package com.example.wanandroid_vpa.home.fragment

import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.base.widget.BaseSingleListFragment
import com.example.wanandroid_vpa.home.viewmodel.HomeModel
import kotlinx.android.synthetic.main.fragment_singlelist.*

/**
 * Created by geegumb on 2020/11/30
 *
 */
class HomeFragment : BaseSingleListFragment<HomeModel>(HomeModel::class.java) {

    override fun requestData() {
        mViewModel.requestBanner()
        mViewModel.requestArticle()
    }

    override fun getTabName(): CharSequence? {
        return context?.resources?.getText(R.string.mainPage)
    }

    override fun observe() {
        mViewModel.mBannerBeanList.observe(this, {
            swipeRefreshView.isRefreshing = false
            mAdapter.addBanners(it)
        })
        mViewModel.mArticleBeanList.observe(this, {
            swipeRefreshView.isRefreshing = false
            mAdapter.addArticles(it)
        })
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}