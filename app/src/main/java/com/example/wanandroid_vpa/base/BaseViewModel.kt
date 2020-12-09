package com.example.wanandroid_vpa.base

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

/**
 * Created by geegumb on 2020/12/9
 *
 */
open class BaseViewModel : ViewModel() {
    protected var mCurrentPage = 0

    fun resetCurrentPage() { mCurrentPage = 0 }
}