package com.example.wanandroid_vpa.qa

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wanandroid_vpa.home.bean.ArticleBean
import kotlinx.coroutines.launch

class QAViewModel : ViewModel() {
    val mQAList = MutableLiveData<List<ArticleBean>?>()
    private val mRepository = QARepository()
    var mCurrentPage = 0

    fun requestQAList() {
        viewModelScope.launch { mQAList.value = mRepository.requestQAList(mCurrentPage++) }
    }
}
