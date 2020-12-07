package com.example.wanandroid_vpa.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.ext.addOnLoadMoreListener
import com.example.wanandroid_vpa.home.adapter.HomeRvAdapter
import com.example.wanandroid_vpa.home.viewmodel.HomeModel
import com.example.wanandroid_vpa.main.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by geegumb on 2020/11/30
 *
 */
class HomeFragment: Fragment() {

    private lateinit var mViewModel: HomeModel
    private val mAdapter = HomeRvAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerview.adapter = mAdapter
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.addOnLoadMoreListener { mViewModel.requestArticle() }
        swipeRefreshView.setOnRefreshListener { refresh() }
        tvTabName.text = context?.resources?.getText(R.string.mainPage)

        mViewModel = ViewModelProvider(this).get(HomeModel::class.java)
        mViewModel.mBannerBeanList.observe(this) {
            mAdapter.addBanners(it)
            (activity as MainActivity).showLoading(false)
        }
        mViewModel.mArticleBeanList.observe(this) {
            mAdapter.addArticles(it)
            (activity as MainActivity).showLoading(false)
        }
        requestData()
    }

    private fun refresh() {
        mAdapter.removeBanners()
        mAdapter.removeArticles()
        mViewModel.mCurrentArticlePage = 0
        swipeRefreshView.isRefreshing = true
        (activity as MainActivity).showLoading(true)
        try {
            requestData()
        } catch (t: Throwable) {
            t.printStackTrace()
        } finally {
            swipeRefreshView.isRefreshing = false
            (activity as MainActivity).showLoading(false)
        }
    }

    private fun requestData() {
        mViewModel.requestBanner()
        mViewModel.requestArticle()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}