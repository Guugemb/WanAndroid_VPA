package com.example.wanandroid_vpa.home

/**
 * Created by geegumb on 2020/11/30
 *
 */
data class BannerBeanWrapper(var bannerBeanList: List<BannerBean>?) {
    data class BannerBean (
        var title: String?,
        var url: String?,
        var imagePath: String?
    )
}