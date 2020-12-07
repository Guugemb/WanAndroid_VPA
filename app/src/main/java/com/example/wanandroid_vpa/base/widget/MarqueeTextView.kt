package com.example.wanandroid_vpa.base.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * Created by geegumb on 2020/12/3
 *
 */
class MarqueeTextView(c: Context, attrs: AttributeSet?) : AppCompatTextView(c, attrs) {
    override fun isFocused(): Boolean {
        return true
    }
}