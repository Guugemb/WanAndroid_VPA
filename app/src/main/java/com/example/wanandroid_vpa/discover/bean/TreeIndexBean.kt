package com.example.wanandroid_vpa.discover.bean

import com.example.wanandroid_vpa.base.BaseBean


data class TreeJsonWrapper(val data: List<TreeIndexBean>) : BaseBean()
data class TreeIndexBean(
    var name: String? = null,
    val children: List<TreeItemBean>,
    var isSelected: Boolean = false
)
data class TreeItemBean(
    var name: String? = null,
    var id: Int
)
