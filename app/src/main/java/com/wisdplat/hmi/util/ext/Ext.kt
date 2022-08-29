package com.wisdplat.hmi.util.ext

import android.content.Context
import android.content.pm.ApplicationInfo

/**
 * @author songyinan
 * @email songynes@foxmail.com
 * @creat 2022-08-08
 * */
object Ext {
    /**
     * 判断是否为debug版本
     */
    fun Context.isDebugVersion(): Boolean =
        runCatching {
            (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0
        }.getOrDefault(false)

    inline fun <R> notNull(vararg args: Any?, block: () -> R) =
        when {
            args.filterNotNull().size == args.size -> block()
            else -> null
        }
}

