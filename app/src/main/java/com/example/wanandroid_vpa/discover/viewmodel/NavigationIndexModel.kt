package com.example.wanandroid_vpa.discover.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.wanandroid_vpa.base.BaseViewModel
import com.example.wanandroid_vpa.base.launch
import com.example.wanandroid_vpa.discover.bean.NavigationIndexBean
import com.example.wanandroid_vpa.discover.repository.NavigationRepository

/**
 * Created by geegumb on 2020/12/21
 *
 */
class NavigationIndexModel : BaseViewModel() {

    val mDatas = MutableLiveData<List<NavigationIndexBean>>()
    private val mRepository = NavigationRepository()

    fun requestNavigationIndex() {
        launch { mDatas.value = mRepository.requestNavigationIndex() }
    }
}