package com.example.wanandroid_vpa.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.wanandroid_vpa.R

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(HomeModel::class.java)
        mViewModel.mBannerBeanList.observe(this, Observer {
            mAdapter.addBanners(it)
        })
        requestData()
    }

    private fun requestData() {
        mViewModel.requestBanner()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}