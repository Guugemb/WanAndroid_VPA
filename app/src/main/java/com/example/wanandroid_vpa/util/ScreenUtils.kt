package com.example.wanandroid_vpa.util

import android.content.Context
import android.util.DisplayMetrics

import android.view.WindowManager




/**
 * Created by geegumb on 2020/12/1
 *
 */
object ScreenUtils {
    /**
     * 获得屏幕高度
     */
    fun getScreenWidth(context: Context): Int {
        val wm = context
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.widthPixels
    }

    /**
     * 获得屏幕宽度
     */
    fun getScreenHeight(context: Context): Int {
        val wm = context
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.heightPixels
    }
}