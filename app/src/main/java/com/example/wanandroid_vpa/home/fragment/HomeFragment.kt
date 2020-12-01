package com.example.wanandroid_vpa.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.home.adapter.HomeRvAdapter
import com.example.wanandroid_vpa.home.viewmodel.HomeModel
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
        rcv_home.adapter = mAdapter
        rcv_home.layoutManager = LinearLayoutManager(context)
        mViewModel = ViewModelProvider(this).get(HomeModel::class.java)
        mViewModel.mBannerBeanList.observe(this, {
            mAdapter.addBanners(it)
        })
        mViewModel.mArticleBeanList.observe(this, {
            mAdapter.addArticles(it)
        })
        requestData()
    }

    private fun requestData() {
        mViewModel.requestBanner()
        mViewModel.requestArticle(0)
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}