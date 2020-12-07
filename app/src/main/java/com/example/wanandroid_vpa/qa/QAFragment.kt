package com.example.wanandroid_vpa.qa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.ext.addOnLoadMoreListener
import com.example.wanandroid_vpa.home.adapter.HomeRvAdapter
import com.example.wanandroid_vpa.main.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_home.*

class QAFragment : Fragment() {
    private lateinit var mViewModel : QAViewModel
    private val mAdapter = HomeRvAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerview.adapter = mAdapter
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.addOnLoadMoreListener { requestData() }
        swipeRefreshView.setOnRefreshListener { refresh() }
        tvTabName.text = context?.resources?.getText(R.string.qa)

        mViewModel = ViewModelProvider(this).get(QAViewModel::class.java)
        mViewModel.mQAList.observe(this) {
            mAdapter.addArticles(it)
        }
        requestData()
    }

    private fun refresh() {
        mAdapter.removeArticles()
        mViewModel.mCurrentPage = 0
        swipeRefreshView.isRefreshing = true
        (activity as MainActivity).showLoading(true)
        try {
            requestData()
        } catch (t: Throwable) {
            t.printStackTrace()
        } finally {
            swipeRefreshView.isRefreshing = false
            (activity as MainActivity).showLoading(false)
        }
    }

    private fun requestData() {
        mViewModel.requestQAList()
    }

    companion object {
        fun newInstance() = QAFragment()
    }
}