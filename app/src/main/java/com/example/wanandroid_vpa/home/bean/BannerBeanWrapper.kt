package com.example.wanandroid_vpa.home.bean

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wanandroid_vpa.base.BaseBean
import java.io.Serializable

/**
 * Created by geegumb on 2020/11/30
 *
 */
data class BannerBeanWrapper(var data: List<BannerBean>?) : BaseBean() {
    @Entity(tableName = "HomeBanner")
    data class BannerBean (
        var title: String?,
        @PrimaryKey
        var url: String,
        var imagePath: String?
    ) : Serializable
}