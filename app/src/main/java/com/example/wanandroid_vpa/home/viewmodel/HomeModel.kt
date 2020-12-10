package com.example.wanandroid_vpa.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.wanandroid_vpa.base.BaseViewModel
import com.example.wanandroid_vpa.home.bean.ArticleBean
import com.example.wanandroid_vpa.home.bean.BannerBeanWrapper.BannerBean
import com.example.wanandroid_vpa.home.repository.HomeRepository
import kotlinx.coroutines.launch

/**
 * Created by geegumb on 2020/11/30
 *
 */
class HomeModel : BaseViewModel() {

    val mBannerBeanList = MutableLiveData<List<BannerBean>?>()
    val mArticleBeanCacheList = MutableLiveData<List<ArticleBean>>()
    val mArticleBeanList = MutableLiveData<List<ArticleBean>>()

    private val mRepository = HomeRepository()

    fun requestBanner() = viewModelScope.launch {
        launch { mBannerBeanList.value = mRepository.requestBannerFromNet() }
        launch { mBannerBeanList.value = mRepository.readBannerFromDataBase() }
    }

    fun requestArticleFromNet() = viewModelScope.launch {
        mArticleBeanList.value = mRepository.requestArticleFromNet(mCurrentPage++)
    }

    fun requestArticleFromCache() = viewModelScope.launch {
        mArticleBeanCacheList.value = mRepository.readArticleFromDatabase()
    }

    fun requestData() {
        requestBanner()
        requestArticleFromNet()
        requestArticleFromCache()
    }

}