package com.example.wanandroid_vpa.home.repository

import com.example.wanandroid_vpa.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

/**
 * Created by geegumb on 2020/11/30
 *
 */
class HomeRepository {

    suspend fun requestBanner() = withContext(Dispatchers.IO) {
        val deffered = async { NetworkService.apiService.getBanner() }
        return@withContext deffered.await().data
    }
}