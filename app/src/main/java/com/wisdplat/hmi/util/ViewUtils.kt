package com.wisdplat.hmi.util

import android.content.Context
import com.wisdplat.hmi.App


/**
 *
@author tanqianpeng
@email  tqp0507@qq.com
@create 2022-8-1 12:30
 *
 */
object ViewUtils {

    fun dp2px(dpValue: Float): Int {
        val scale: Float = App.context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }


}