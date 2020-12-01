package com.example.wanandroid_vpa.home.repository

import android.util.Log
import com.example.wanandroid_vpa.home.bean.ArticleBean
import com.example.wanandroid_vpa.home.bean.BannerBeanWrapper
import com.example.wanandroid_vpa.home.bean.BannerBeanWrapper.BannerBean
import com.example.wanandroid_vpa.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by geegumb on 2020/11/30
 *
 */
class HomeRepository {

    suspend fun requestBanner() : List<BannerBean>? =
         withContext(Dispatchers.IO) { NetworkService.apiService.getBanner() }.data


    suspend fun requestArticle(page: Int) : List<ArticleBean>? {
        var res: List<ArticleBean>? = null
        try {
            res = withContext(Dispatchers.IO) {
                NetworkService.apiService.getHomeArticle(page) }.data.datas
        } catch (t: Throwable) {
            Log.e("zhujin", "runSafe: throws $t" )
        }
        return res
    }
}