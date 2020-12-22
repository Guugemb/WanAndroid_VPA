package com.example.wanandroid_vpa.discover.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.wanandroid_vpa.base.BaseViewModel
import com.example.wanandroid_vpa.base.launch
import com.example.wanandroid_vpa.discover.bean.ProjectIndexBean
import com.example.wanandroid_vpa.discover.bean.ProjectItemBean
import com.example.wanandroid_vpa.discover.repository.ProjectRepository

/**
 * Created by geegumb on 2020/12/22
 *
 */
class ProjectModel : BaseViewModel() {
    val mProjectIndexList = MutableLiveData<List<ProjectIndexBean>>()
    val mProjectItemList = MutableLiveData<List<ProjectItemBean>>()
    var mCurrentCid: Int = 0
    private val mRepository = ProjectRepository()

    fun requestProjectIndex() {
        launch { mProjectIndexList.value = mRepository.requestProjectIndex() }
    }

    fun requestProjectItem() {
        launch { mProjectItemList.value = mRepository.requestProjectItem(mCurrentPage++, mCurrentCid) }
    }
}