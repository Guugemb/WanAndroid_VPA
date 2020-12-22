package com.example.wanandroid_vpa.discover.bean

/**
 * Created by geegumb on 2020/12/22
 *
 */
data class ProjectIndexWrapper(val data: List<ProjectIndexBean>)
data class ProjectIndexBean(
    val id: Int,
    val name: String,
    var isSelected: Boolean
)