package com.example.wanandroid_vpa.discover.repository

import com.example.wanandroid_vpa.base.BaseRepository
import com.example.wanandroid_vpa.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by geegumb on 2020/12/22
 *
 */
class ProjectRepository : BaseRepository() {

    suspend fun requestProjectIndex() = withContext(Dispatchers.IO) {
        return@withContext NetworkService.apiService.getProjectIndex().data
    }

    suspend fun requestProjectItem(page: Int, cid: Int) = withContext(Dispatchers.IO) {
        return@withContext NetworkService.apiService.getProjectItem(page, cid).data.datas
    }
}