package com.example.wanandroid_vpa.base.widget

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.base.BaseViewModel
import com.example.wanandroid_vpa.base.BaseViewModelFragment
import com.example.wanandroid_vpa.ext.addOnLoadMoreListener
import com.example.wanandroid_vpa.home.adapter.SingleListAdapter
import kotlinx.android.synthetic.main.fragment_singlelist.*

/**
 * Created by geegumb on 2020/12/9
 *
 */
abstract class BaseSingleListFragment<VM : BaseViewModel>(clazz: Class<VM>) :
    BaseViewModelFragment<VM>(clazz) {

    protected val mAdapter = SingleListAdapter()

    override fun getLayoutRes(): Int {
        return R.layout.fragment_singlelist
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observe()
        requestData()
    }

    abstract fun observe()

    abstract fun requestData()

    abstract fun getTabName(): CharSequence?

    @CallSuper
    protected fun initView() {
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = mAdapter
        recyclerview.addOnLoadMoreListener { requestData() }
        swipeRefreshView.setOnRefreshListener { onRefresh() }
        tvTabName.text = getTabName()
    }

    @CallSuper
    protected fun onRefresh() {
        mAdapter.clear()
        mViewModel.resetCurrentPage()
        swipeRefreshView.isRefreshing = true
        // todo activity show loading dialog
        try {
            requestData()
        } catch (t: Throwable) {
            t.printStackTrace()
        } finally {
            swipeRefreshView.isRefreshing = false
            // todo activity dismiss loading dialog
        }
    }
}