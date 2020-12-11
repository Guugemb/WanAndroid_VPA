package com.example.wanandroid_vpa.network

import androidx.room.*
import com.example.wanandroid_vpa.home.bean.ArticleBean
import com.example.wanandroid_vpa.home.bean.BannerJsonWrapper.BannerBean

/**
 * Created by geegumb on 2020/12/9
 *
 */
@Dao
abstract class HomeBannerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(list: List<BannerBean>)

    @Delete
    abstract fun delete(list: List<BannerBean>)

    @Update
    abstract fun update(list: List<BannerBean>)

    @Query("SELECT * FROM HomeBanner")
    abstract fun getBannerList(): MutableList<BannerBean>
}

@Dao
abstract class HomeArticleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(list: List<ArticleBean>)

    @Delete
    abstract fun delete(bean: ArticleBean)

    @Update
    abstract fun update(bean: ArticleBean)

    @Query("SELECT * FROM HomeArticle")
    abstract fun getArticleList(): MutableList<ArticleBean>
}