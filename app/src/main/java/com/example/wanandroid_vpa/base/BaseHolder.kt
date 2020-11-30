package com.example.wanandroid_vpa.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by geegumb on 2020/11/30
 *
 */
abstract class BaseHolder<T>(v: View): RecyclerView.ViewHolder(v) {
    abstract fun bind(t: T)
}