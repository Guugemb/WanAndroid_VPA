package com.example.wanandroid_vpa.network

import com.example.wanandroid_vpa.discover.bean.TreeJsonWrapper
import com.example.wanandroid_vpa.home.bean.BannerJsonWrapper
import com.example.wanandroid_vpa.home.bean.ArticleJsonWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by geegumb on 2020/11/30
 *
 */
interface ApiService {

    @GET("/banner/json")
    suspend fun getBanner(): BannerJsonWrapper

    @GET("article/list/{page}/json")
    suspend fun getHomeArticle(@Path("page") page: Int): ArticleJsonWrapper

    @GET("/wenda/list/{page}/json")
    suspend fun getQAList(@Path("page") page: Int): ArticleJsonWrapper

    @GET("/tree/json")
    suspend fun getTreeList(): TreeJsonWrapper

    @GET("article/list/{page}/json")
    suspend fun getTreeItemDetail(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): ArticleJsonWrapper
}