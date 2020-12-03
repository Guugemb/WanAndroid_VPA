package com.example.wanandroid_vpa.home.bean

import com.example.wanandroid_vpa.base.BaseBean
import java.io.Serializable

/**
 * Created by geegumb on 2020/11/30
 *
 */
data class BannerBeanWrapper(var data: List<BannerBean>?) : BaseBean() {
    data class BannerBean (
        var title: String?,
        var url: String?,
        var imagePath: String?
    ) : Serializable
}