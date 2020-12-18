package com.example.wanandroid_vpa.discover.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.wanandroid_vpa.base.BaseViewModel
import com.example.wanandroid_vpa.discover.repository.TreeRepository
import com.example.wanandroid_vpa.discover.bean.TreeIndexBean
import kotlinx.coroutines.launch

/**
 * Created by geegumb on 2020/12/8
 *
 */
class TreeIndexModel : BaseViewModel() {

    val mDatas = MutableLiveData<List<TreeIndexBean>>()
    private val mRepository = TreeRepository()

    fun requestTreeIndex(){
        viewModelScope.launch { mDatas.value = mRepository.requestTreeList() }
    }
}