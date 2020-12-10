package com.example.wanandroid_vpa.qa

import androidx.lifecycle.Observer
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.base.widget.BaseSingleListFragment

class QAFragment : BaseSingleListFragment<QAModel>(QAModel::class.java) {

    override fun getLayoutRes(): Int {
        return R.layout.fragment_singlelist
    }

    override fun requestData() {
        mViewModel.requestQAList()
    }

    override fun onLoadMore() {
        requestData()
    }

    override fun observe() {
        mViewModel.mQAList.observe(this, Observer {
            mAdapter.addArticlesFromNet(it)
        })
    }

    override fun getTabName(): CharSequence? {
        return context?.resources?.getText(R.string.qa)
    }

    companion object {
        fun newInstance() = QAFragment()
    }
}