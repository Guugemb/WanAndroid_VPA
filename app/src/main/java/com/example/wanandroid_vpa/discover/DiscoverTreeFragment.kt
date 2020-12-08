package com.example.wanandroid_vpa.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wanandroid_vpa.R
import kotlinx.android.synthetic.main.fragment_discover_structure.*

/**
 * Created by geegumb on 2020/12/7
 *
 */
class DiscoverTreeFragment : Fragment(), IndexClickCallBack {

    private val mIndexAdapter = TreeIndexAdapter(this)
    private val mItemAdapter = TreeItemAdapter()
    private lateinit var mViewModel: StructureViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_discover_structure, container, false)
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

        mViewModel = ViewModelProvider(this).get(StructureViewModel::class.java)
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