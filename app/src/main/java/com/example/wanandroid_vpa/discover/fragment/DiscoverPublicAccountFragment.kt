package com.example.wanandroid_vpa.discover.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.base.BaseViewModelFragment
import com.example.wanandroid_vpa.discover.adapter.DiscoverIndexAdapter
import com.example.wanandroid_vpa.discover.adapter.IndexClickCallBack
import com.example.wanandroid_vpa.discover.bean.TreeIndexBean
import com.example.wanandroid_vpa.discover.viewmodel.PublicAccountModel
import com.example.wanandroid_vpa.ext.addOnLoadMoreListener
import com.example.wanandroid_vpa.home.adapter.SingleListAdapter
import kotlinx.android.synthetic.main.fragment_discover_structure.*

/**
 * Created by geegumb on 2020/12/7
 *
 */
class DiscoverPublicAccountFragment : BaseViewModelFragment<PublicAccountModel>
    (PublicAccountModel::class.java), IndexClickCallBack<TreeIndexBean> {

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
        rcv_content.addOnLoadMoreListener {
            mViewModel.mCurrentCid?.let { mViewModel.requestPublicAccountArticle(it) }
        }

        mViewModel.mDatas.observe(this) {
            it[0].isSelected = true
            mIndexAdapter.add(it)
            mViewModel.mCurrentCid = it[0].id
            mViewModel.requestPublicAccountArticle(it[0].id)
        }
        mViewModel.mArticles.observe(this) {
            mItemAdapter.addArticlesFromNet(it)
        }
        mViewModel.requestPublicAccountIndex()
    }

    override fun onIndexClick(bean: TreeIndexBean) {
        mViewModel.resetCurrentPage()
        mViewModel.mCurrentCid = bean.id
        mViewModel.requestPublicAccountArticle(bean.id)
        mItemAdapter.clear()
        mIndexAdapter.updateItemSelected(bean)
    }

    companion object {
        fun newInstance() = DiscoverPublicAccountFragment()
    }
}