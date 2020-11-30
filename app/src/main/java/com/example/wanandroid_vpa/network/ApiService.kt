package com.example.wanandroid_vpa.network

import com.example.wanandroid_vpa.home.BannerBeanWrapper
import retrofit2.http.GET

/**
 * Created by geegumb on 2020/11/30
 *
 */
interface ApiService {

    @GET("banner/json")
    suspend fun getBanner(): BannerBeanWrapper
}