package com.example.wanandroid_vpa.discover

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * Created by geegumb on 2020/12/8
 *
 */
class StructureViewModel : ViewModel() {

    val mDatas = MutableLiveData<List<TreeIndexBean>>()
    private val mRepository = TreeRepository()

    fun requestTreeIndex(){
        viewModelScope.launch { mDatas.value = mRepository.requestTreeList() }
    }
}