package com.example.wanandroid_vpa.network

import androidx.room.*
import com.example.wanandroid_vpa.home.bean.ArticleBean

/**
 * Created by geegumb on 2020/12/9
 *
 */
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