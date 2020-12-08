package com.example.wanandroid_vpa.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.discover.TreeItemDetailActivity.Companion.FLAG_CID
import com.example.wanandroid_vpa.discover.TreeItemDetailActivity.Companion.FLAG_TITLE
import com.example.wanandroid_vpa.ext.addOnLoadMoreListener
import com.example.wanandroid_vpa.home.adapter.HomeRvAdapter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by geegumb on 2020/12/8
 *
 */
class TreeItemDetailFragment : Fragment() {

    private val mAdapter = HomeRvAdapter()
    private val mViewModel = TreeItemDetailModel()
    private var cid: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cid = arguments?.getInt(FLAG_CID)
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = mAdapter
        recyclerview.addOnLoadMoreListener { mViewModel.requestTreeItemDetail(cid!!) }
        swipeRefreshView.setOnRefreshListener { refresh() }

        mViewModel.mDatas.observe(this) {
            swipeRefreshView.isRefreshing = false
            mAdapter.addArticles(it)
        }
        mViewModel.requestTreeItemDetail(cid!!)
        arguments?.getString(FLAG_TITLE).let { tvTabName.text = it }
    }

    private fun refresh() {
        mAdapter.removeArticles()
        mViewModel.mCurrentPage = 0
        swipeRefreshView.isRefreshing = true
        try {
            mViewModel.requestTreeItemDetail(cid!!)
        } catch (t: Throwable) {
            t.printStackTrace()
        } finally {
            swipeRefreshView.isRefreshing = false
        }
    }

    companion object {
        fun newInstance() = TreeItemDetailFragment()
    }
}