package com.example.wanandroid_vpa.home.repository

import com.example.wanandroid_vpa.base.BaseRepository
import com.example.wanandroid_vpa.home.bean.ArticleBean
import com.example.wanandroid_vpa.home.bean.BannerBeanWrapper.BannerBean
import com.example.wanandroid_vpa.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by geegumb on 2020/11/30
 *
 */
class HomeRepository : BaseRepository() {

    // todo runSafe{ ... }
    suspend fun requestBanner(): List<BannerBean>? = withContext(Dispatchers.IO) {
        mApiService.getBanner()
    }.data

    suspend fun requestArticleByNet(page: Int) : List<ArticleBean> {
        val res = withContext(Dispatchers.IO) {
            mApiService.getHomeArticle(page)
        }.data.datas
        writeArticleToDatabase(res)
        return res
    }

    private fun writeArticleToDatabase(list: List<ArticleBean>?) =
        list?.let { mDatabase.homeArticleDao.insert(it) }

    fun readArticleFromDatabase(): List<ArticleBean> {
        return mDatabase.homeArticleDao.getArticleList()
    }
}
