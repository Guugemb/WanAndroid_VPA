package com.example.wanandroid_vpa.base

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by geegumb on 2020/12/9
 *
 */
abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseHolder<T>>() {

    abstract fun clear()
}