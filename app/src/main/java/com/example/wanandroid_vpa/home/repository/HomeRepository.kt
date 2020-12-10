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
    suspend fun requestBannerFromNet(): List<BannerBean>?  {
        val res = withContext(Dispatchers.IO) {
            mApiService.getBanner()
        }.data
        writeBannerToDatabase(res)
        return res
    }

    private fun writeBannerToDatabase(list: List<BannerBean>?) {
        list?.let { mDatabase.homeBannerDao.insert(it) }
    }

    fun readBannerFromDataBase() : List<BannerBean> {
        return mDatabase.homeBannerDao.getBannerList()
    }

    suspend fun requestArticleFromNet(page: Int) : List<ArticleBean> {
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
