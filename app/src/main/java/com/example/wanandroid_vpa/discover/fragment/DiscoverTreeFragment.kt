package com.example.wanandroid_vpa.discover.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.base.BaseViewModelFragment
import com.example.wanandroid_vpa.discover.adapter.IndexClickCallBack
import com.example.wanandroid_vpa.discover.adapter.TreeIndexAdapter
import com.example.wanandroid_vpa.discover.adapter.TreeItemAdapter
import com.example.wanandroid_vpa.discover.bean.TreeIndexBean
import com.example.wanandroid_vpa.discover.viewmodel.TreeIndexModel
import kotlinx.android.synthetic.main.fragment_discover_structure.*

/**
 * Created by geegumb on 2020/12/7
 *
 */
class DiscoverTreeFragment : BaseViewModelFragment<TreeIndexModel>
    (TreeIndexModel::class.java), IndexClickCallBack {

    private val mIndexAdapter = TreeIndexAdapter(this)
    private val mItemAdapter = TreeItemAdapter()

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
            mItemAdapter.add(it[0].children)
        }
        mViewModel.requestTreeIndex()
    }

    override fun onIndexClick(bean: TreeIndexBean) {
        mItemAdapter.clear()
        mItemAdapter.add(bean.children)
        mIndexAdapter.updateItemSelected(bean)
    }

    companion object {
        fun newInstance() = DiscoverTreeFragment()
    }
}