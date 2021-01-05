package com.example.wanandroid_vpa.discover.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.base.BaseViewModelFragment
import com.example.wanandroid_vpa.discover.adapter.DiscoverIndexAdapter
import com.example.wanandroid_vpa.discover.adapter.IndexClickCallBack
import com.example.wanandroid_vpa.discover.bean.NavigationIndexBean
import com.example.wanandroid_vpa.discover.viewmodel.NavigationIndexModel
import com.example.wanandroid_vpa.home.adapter.SingleListAdapter
import kotlinx.android.synthetic.main.fragment_discover_structure.*

/**
 * Created by geegumb on 2020/12/7
 *
 */
class DiscoverNavigationFragment : BaseViewModelFragment<NavigationIndexModel>
    (NavigationIndexModel::class.java), IndexClickCallBack<NavigationIndexBean> {

    private val mIndexAdapter = DiscoverIndexAdapter(this)
    private val mItemAdapter = SingleListAdapter()

    override fun getLayoutRes(): Int {
        return R.layout.fragment_discover_structure
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rcv_index.adapter = mIndexAdapter
        rcv_index.layoutManager = StaggeredGridLayoutManager(
            3,
            StaggeredGridLayoutManager.HORIZONTAL
        )
        rcv_content.adapter = mItemAdapter
        rcv_content.layoutManager = LinearLayoutManager(context)

        mViewModel.mDatas.observe(this) {
            it[0].isSelected = true
            mIndexAdapter.add(it)
            mItemAdapter.addArticlesFromNet(it[0].articles)
        }
        mViewModel.requestNavigationIndex()
    }

    override fun onIndexClick(bean: NavigationIndexBean) {
        mItemAdapter.clear()
        mItemAdapter.addArticlesFromNet(bean.articles)
        mIndexAdapter.updateItemSelected(bean)
    }

    companion object {
        fun newInstance() = DiscoverNavigationFragment()
    }
}