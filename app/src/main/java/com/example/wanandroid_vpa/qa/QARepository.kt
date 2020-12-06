package com.example.wanandroid_vpa.qa

import com.example.wanandroid_vpa.home.bean.ArticleBean
import com.example.wanandroid_vpa.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QARepository {

    suspend fun requestQAList(page: Int): List<ArticleBean>? = withContext(Dispatchers.IO) {
        NetworkService.apiService.getQAList(page)
    }.data.datas

}
