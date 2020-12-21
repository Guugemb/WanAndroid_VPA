package com.example.wanandroid_vpa.discover.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.wanandroid_vpa.base.BaseViewModel
import com.example.wanandroid_vpa.base.launch
import com.example.wanandroid_vpa.discover.bean.TreeIndexBean
import com.example.wanandroid_vpa.discover.repository.PublicAccountRepository
import com.example.wanandroid_vpa.home.bean.ArticleBean

/**
 * Created by geegumb on 2020/12/21
 *
 */
class PublicAccountModel : BaseViewModel() {
    val mDatas = MutableLiveData<List<TreeIndexBean>>()
    val mArticles = MutableLiveData<List<ArticleBean>>()
    var mCurrentCid: Int? = null
    private val mRepository = PublicAccountRepository()

    fun requestPublicAccountIndex() {
        launch { mDatas.value = mRepository.requestPublicAccountIndex() }
    }

    fun requestPublicAccountArticle(id: Int) {
        launch { mArticles.value = mRepository.requestPublicAccountArticle(id, mCurrentPage++) }
    }
}