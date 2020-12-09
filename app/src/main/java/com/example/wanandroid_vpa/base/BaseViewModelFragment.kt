package com.example.wanandroid_vpa.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by geegumb on 2020/12/9
 *
 */
abstract class BaseViewModelFragment<VM : ViewModel>
    (private val clazz: Class<VM>): BaseFragment() {

    protected lateinit var mViewModel: VM

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewModel = ViewModelProvider(this).get(clazz)
    }
}