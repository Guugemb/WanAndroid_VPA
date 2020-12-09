package com.example.wanandroid_vpa.home.viewmodel

import android.util.Log
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
    val mArticleBeanList = MutableLiveData<List<ArticleBean>>()

    private val mRepository = HomeRepository()

    fun requestBanner() = viewModelScope.launch {
        mBannerBeanList.value = mRepository.requestBanner()
    }

    fun requestArticle() = viewModelScope.launch {
        try {
            mArticleBeanList.value = mRepository.readArticleFromDatabase()
            mArticleBeanList.value = mRepository.requestArticleByNet(mCurrentPage++)
        } catch (t: Throwable) {
            Log.e("zhujin", "requestArticle: $t" )
            t.printStackTrace()
        }
    }
}