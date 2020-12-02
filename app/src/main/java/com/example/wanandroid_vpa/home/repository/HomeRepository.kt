package com.example.wanandroid_vpa.home.repository

import com.example.wanandroid_vpa.home.bean.BannerBeanWrapper.BannerBean
import com.example.wanandroid_vpa.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by geegumb on 2020/11/30
 *
 */
class HomeRepository {

    // todo runSafe{ ... }
    suspend fun requestBanner(): List<BannerBean>? = withContext(Dispatchers.IO) {
        NetworkService.apiService.getBanner()
    }.data

    suspend fun requestArticle(page: Int) = withContext(Dispatchers.IO) {
        NetworkService.apiService.getHomeArticle(page)
    }.data.datas

}