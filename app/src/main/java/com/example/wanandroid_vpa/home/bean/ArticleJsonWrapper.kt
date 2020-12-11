package com.example.wanandroid_vpa.home.bean

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wanandroid_vpa.base.BaseBean
import java.io.Serializable

/**
 * Created by geegumb on 2020/12/1
 *
 */
data class ArticleJsonWrapper(var data: ArticleJsonWrapper1) : BaseBean()
data class ArticleJsonWrapper1(var curPage: Int, var datas: List<ArticleBean>): Serializable
@Entity(tableName = "HomeArticle")
data class ArticleBean(
    @PrimaryKey
    var id: Int,
    var author: String?,
    var shareUser: String?,
    var chapterName: String?, // 右上角描述小标签
    var title: String?, // 标题
    var fresh: Boolean?, // 右上角新帖小标签
    var link: String?, // 跳转链接
    var niceDate: String?, // 发布时间
    var superChapterName: String?, // 右下角分类
    var collect: Boolean?, // 是否已经收藏
) : Serializable