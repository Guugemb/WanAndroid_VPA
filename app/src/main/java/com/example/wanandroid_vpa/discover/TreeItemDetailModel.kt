package com.example.wanandroid_vpa.discover

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wanandroid_vpa.base.BaseViewModel
import com.example.wanandroid_vpa.home.bean.ArticleBean
import com.example.wanandroid_vpa.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by geegumb on 2020/12/8
 *
 */
class TreeItemDetailModel : BaseViewModel() {

    val mDatas = MutableLiveData<List<ArticleBean>>()
    private val mDetailRepository = TreeItemDetailRepository()

    fun requestTreeItemDetail(cid: Int) = viewModelScope.launch {
        mDatas.value = mDetailRepository.requestTreeItemDetail(mCurrentPage++, cid)
    }
}

class TreeItemDetailRepository {
    suspend fun requestTreeItemDetail(page: Int, cid: Int) = withContext(Dispatchers.IO) {
        NetworkService.apiService.getTreeItemDetail(page, cid).data.datas
    }
}