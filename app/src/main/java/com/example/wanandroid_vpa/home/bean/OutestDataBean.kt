package com.example.wanandroid_vpa.home.bean

import com.example.wanandroid_vpa.base.BaseBean
import java.io.Serializable

/**
 * Created by geegumb on 2020/12/1
 *
 */
data class OutestDataBean(var data: DataBean) : BaseBean()
data class DataBean(var curPage: Int, var datas: List<ArticleBean>): Serializable
data class ArticleBean(
    var author: String?,
    var shareUser: String?,
    var chapterName: String?, // 右上角描述小标签
    var title: String?, // 标题
    var fresh: Boolean?, // 右上角新帖小标签
    var link: String?, // 跳转链接
    var niceShareDate: String?, // 发布时间
    var superChapterName: String?, // 右下角分类
    var collect: Boolean?, // 是否已经收藏
) : Serializable