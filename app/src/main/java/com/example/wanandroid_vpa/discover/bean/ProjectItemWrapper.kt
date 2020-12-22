package com.example.wanandroid_vpa.discover.bean

/**
 * Created by geegumb on 2020/12/22
 *
 */
data class ProjectItemWrapper(val data: ProjectItemWrapper1)
data class ProjectItemWrapper1(
    val datas: List<ProjectItemBean>,
    val over: Boolean
)
data class ProjectItemBean(
    val author: String,
    val title: String,
    val desc: String,
    val fresh: Boolean,
    val envelopePic: String,
    val niceDate: String,
    val superChapterName: String,
    val projectLink: String,
    val link: String
)