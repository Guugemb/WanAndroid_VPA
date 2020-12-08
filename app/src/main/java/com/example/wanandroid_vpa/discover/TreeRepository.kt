package com.example.wanandroid_vpa.discover

import com.example.wanandroid_vpa.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by geegumb on 2020/12/8
 *
 */
class TreeRepository {
    suspend fun requestTreeList() : List<TreeIndexBean> = withContext(Dispatchers.IO) {
        NetworkService.apiService.getTreeList()
    }.data
}