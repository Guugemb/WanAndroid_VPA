package com.example.wanandroid_vpa

import android.app.Application
import androidx.multidex.MultiDex

/**
 * Created by geegumb on 2020/11/30
 *
 */
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
    }
}