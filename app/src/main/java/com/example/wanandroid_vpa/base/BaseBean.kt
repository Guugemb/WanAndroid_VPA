package com.example.wanandroid_vpa.base

import java.io.Serializable

/**
 * Created by geegumb on 2020/12/1
 *
 */
open class BaseBean : Serializable {
    var errorCode: String? = null
    var errorMsg: String? = null
}