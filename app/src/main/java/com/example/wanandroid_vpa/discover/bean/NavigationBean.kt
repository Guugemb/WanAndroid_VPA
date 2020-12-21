package com.example.wanandroid_vpa.discover.bean

import com.example.wanandroid_vpa.base.BaseBean
import com.example.wanandroid_vpa.home.bean.ArticleBean

/**
 * Created by geegumb on 2020/12/21
 *
 */
data class NavigationWrapper(var data: List<NavigationIndexBean>)
data class NavigationIndexBean(
    var articles: List<ArticleBean>,
    var name: String,
    var isSelected: Boolean
)