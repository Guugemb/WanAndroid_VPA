package com.example.wanandroid_vpa.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Created by geegumb on 2020/12/9
 *
 */
open class BaseViewModel : ViewModel() {
    protected var mCurrentPage = 0

    fun resetCurrentPage() { mCurrentPage = 0 }

    companion object {
        val handler = CoroutineExceptionHandler { _, t ->
            Log.e(BaseViewModel::class.java.simpleName, ": $t", )
        }
    }
}

fun BaseViewModel.launch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return viewModelScope.launch(context, start, block)
}