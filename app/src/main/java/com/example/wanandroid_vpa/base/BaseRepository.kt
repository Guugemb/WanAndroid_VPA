package com.example.wanandroid_vpa.base

import com.example.wanandroid_vpa.MyApplication
import com.example.wanandroid_vpa.network.MyRoomDatabase
import com.example.wanandroid_vpa.network.NetworkService

/**
 * Created by geegumb on 2020/12/9
 *
 */
open class BaseRepository {

    protected val mApiService = NetworkService.apiService
    protected val mDatabase by lazy { MyRoomDatabase.getInstance(MyApplication.INSTANCE) }
}