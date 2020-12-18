package com.example.wanandroid_vpa.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.example.wanandroid_vpa.main.adapter.MainPagerAdapter
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.discover.fragment.DiscoverFragment
import com.example.wanandroid_vpa.home.fragment.HomeFragment
import com.example.wanandroid_vpa.qa.QAFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mFragments = arrayListOf<Fragment>()
    private val mPagerAdapter = MainPagerAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initFragment()
    }

    private fun initFragment() {
        mFragments.apply {
            add(HomeFragment.newInstance())
            add(QAFragment.newInstance())
            add(DiscoverFragment.newInstance())
        }
        mPagerAdapter.addFragments(mFragments)
        vpMain.adapter = mPagerAdapter
        vpMain.isUserInputEnabled = false
    }

    private fun initView() {
        rbMainPage.setOnClickListener { checkAndSwitch(R.id.rbMainPage, 0) }
        rbQA.setOnClickListener { checkAndSwitch(R.id.rbQA, 1) }
        rbDiscover.setOnClickListener { checkAndSwitch(R.id.rbDiscover, 2) }
        rbMine.setOnClickListener { checkAndSwitch(R.id.rbMine, 3) }
        checkAndSwitch(R.id.rbMainPage, 0)
    }

    private fun checkAndSwitch(@IdRes tab: Int, position: Int) {
        radioGroup_bottom.check(tab)
        vpMain.setCurrentItem(position, false)
    }

}