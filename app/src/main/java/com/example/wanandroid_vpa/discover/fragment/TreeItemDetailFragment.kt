package com.example.wanandroid_vpa.discover.fragment

import android.os.Bundle
import com.example.wanandroid_vpa.base.widget.BaseSingleListFragment
import com.example.wanandroid_vpa.discover.activity.TreeItemDetailActivity.Companion.FLAG_CID
import com.example.wanandroid_vpa.discover.activity.TreeItemDetailActivity.Companion.FLAG_TITLE
import com.example.wanandroid_vpa.discover.viewmodel.TreeItemDetailModel
import kotlinx.android.synthetic.main.fragment_singlelist.*

/**
 * Created by geegumb on 2020/12/8
 *
 */
class TreeItemDetailFragment : BaseSingleListFragment<TreeItemDetailModel>
    (TreeItemDetailModel::class.java) {

    private var cid: Int? = null

    override fun observe() =
        mViewModel.mDatas.observe(this, {
            swipeRefreshView.isRefreshing = false
            mAdapter.addArticlesFromNet(it)
        })

    override fun getTabName(): CharSequence? {
        return arguments?.getString(FLAG_TITLE)
    }

    override fun requestData() {
        cid?.let { mViewModel.requestTreeItemDetail(it) }
    }

    override fun onLoadMore() {
        requestData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cid = arguments?.getInt(FLAG_CID)
    }

    companion object {
        fun newInstance() = TreeItemDetailFragment()
    }
}