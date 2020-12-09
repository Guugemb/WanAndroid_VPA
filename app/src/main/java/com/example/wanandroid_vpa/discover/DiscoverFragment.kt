package com.example.wanandroid_vpa.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_discover.*

/**
 * Created by geegumb on 2020/12/7
 *
 */
class DiscoverFragment : BaseFragment() {
    private val mFragments = arrayListOf<Fragment>()
    private val mTitles = arrayOf("体系", "导航", "公众号", "项目分类")

    override fun getLayoutRes(): Int {
        return R.layout.fragment_discover
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFragments.apply {
            add(DiscoverTreeFragment.newInstance())
            add(DiscoverNavigationFragment.newInstance())
            add(DiscoverPublicAccountFragment.newInstance())
            add(DiscoverProjectFragment.newInstance())
        }

        viewPager2.adapter = DiscoverVpAdapter(mFragments, childFragmentManager, lifecycle)
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = mTitles[position]
        }.attach()
    }

    companion object {
        fun newInstance() = DiscoverFragment()
    }

}

class DiscoverVpAdapter(
    private val mFragments: List<Fragment>,
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int {
        return mFragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return mFragments[position]
    }

}