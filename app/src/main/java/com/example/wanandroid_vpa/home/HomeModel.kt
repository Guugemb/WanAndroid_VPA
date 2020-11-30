package com.example.wanandroid_vpa.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wanandroid_vpa.home.BannerBeanWrapper.BannerBean
import kotlinx.coroutines.launch

/**
 * Created by geegumb on 2020/11/30
 *
 */
class HomeModel : ViewModel() {
    val mBannerBeanList = MutableLiveData<List<BannerBean>?>()
    val mArticleBeanList = MutableLiveData<List<ArticleBean>>()

    private val mRepository = HomeRepository()

    fun requestBanner() {
        viewModelScope.launch { mBannerBeanList.value = mRepository.requestBanner().bannerBeanList }
    }

    fun requestArticle() {

    }
}