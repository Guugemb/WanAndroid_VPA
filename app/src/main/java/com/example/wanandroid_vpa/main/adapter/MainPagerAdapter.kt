package com.example.wanandroid_vpa.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by geegumb on 2020/11/30
 *
 */
class MainPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private val mFragments = arrayListOf<Fragment>()

    public fun addFragments(fragments: List<Fragment>) {
        mFragments.addAll(fragments)
    }

    override fun getItemCount(): Int {
        return mFragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return mFragments[position]
    }
}