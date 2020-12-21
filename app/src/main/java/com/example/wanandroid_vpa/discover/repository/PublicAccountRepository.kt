package com.example.wanandroid_vpa.discover.repository

import com.example.wanandroid_vpa.base.BaseRepository
import com.example.wanandroid_vpa.discover.bean.NavigationIndexBean
import com.example.wanandroid_vpa.discover.bean.TreeIndexBean
import com.example.wanandroid_vpa.home.bean.ArticleBean
import com.example.wanandroid_vpa.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by geegumb on 2020/12/21
 *
 */
class PublicAccountRepository : BaseRepository() {

    suspend fun requestPublicAccountIndex(): List<TreeIndexBean> = withContext(Dispatchers.IO) {
        NetworkService.apiService.getPublicAccount()
    }.data

    suspend fun requestPublicAccountArticle(id: Int, page: Int): List<ArticleBean> = withContext(Dispatchers.IO) {
        NetworkService.apiService.getPublicAccountArticle(id, page)
    }.data.datas
}