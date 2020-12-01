package com.example.wanandroid_vpa.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.example.wanandroid_vpa.main.adapter.MainPagerAdapter
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.home.fragment.HomeFragment
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
        mFragments.addAll(listOf(HomeFragment.newInstance()))
        mPagerAdapter.addFragments(mFragments)
        vpMain.adapter = mPagerAdapter
    }

    private fun initView() {
        radioGroup_bottom.setOnClickListener {
            when (it.id) {
                R.id.rbMainPage -> checkAndSwitch(R.id.rbMainPage, 0)
                R.id.rbQA -> checkAndSwitch(R.id.rbQA, 1)
                R.id.rbDiscover -> checkAndSwitch(R.id.rbDiscover, 2)
                R.id.rbMine -> checkAndSwitch(R.id.rbMine, 3)
            }
        }
    }

    private fun checkAndSwitch(@IdRes tab: Int, position: Int) {
        radioGroup_bottom.check(tab)
        vpMain.currentItem = position
    }

}