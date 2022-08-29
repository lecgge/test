package com.wisdplat.hmi.util

import android.util.Log


/**
 * @author songyinan
 * @email songynes@foxmail.com
 * @creat 2022-08-01
 * */

object LogUtil {

    var isDebug: Boolean = false //是否是开发调试环境： true：开启调试；false：关闭调试;


    private const val TAG: String = "HMI" //log 标签

    /**
     * 打印 Verbose级别的 log
     * @param tag
     * @param msg
     */
    fun v(tag: String, msg: String) {
        if (isDebug) {
            Log.v(tag, msg)
        }
    }

    /**
     * 打印 Debug级别的 log
     * @param tag
     * @param msg
     */
    fun d(tag: String, msg: String) {
        if (isDebug) {
            Log.d(tag, msg)
        }
    }

    /**
     * 打印 Info级别的 log
     * @param tag
     * @param msg
     */
    fun i(tag: String, msg: String) {
        if (isDebug) {
            Log.i(tag, msg)
        }
    }

    /**
     * 打印 Warn级别的 log
     * @param tag
     * @param msg
     */
    fun w(tag: String, msg: String) {
        if (isDebug) {
            Log.w(tag, msg)
        }
    }

    /**
     *打印 Error级别的 log
     * @param tag
     * @param msg
     */
    fun e(tag: String, msg: String) {
        if (isDebug) {
            Log.e(tag, msg)
        }
    }


    /**
     * 打印 Verbose级别的 log
     * @param msg
     */
    fun v(msg: String) {
        if (isDebug) {
            Log.v(TAG, msg)
        }
    }

    /**
     * 打印 Debug级别的 log
     * @param msg
     */
    fun d(msg: String) {
        if (isDebug) {
            Log.d(TAG, msg)
        }
    }

    /**
     * 打印 Info级别的 log
     * @param msg
     */
    fun i(msg: String) {
        if (isDebug) {
            Log.i(TAG, msg)
        }
    }

    /**
     * 打印 Warn级别的 log
     * @param msg
     */
    fun w(msg: String) {
        if (isDebug) {
            Log.w(TAG, msg)
        }
    }

    /**
     * 打印 Error级别的 log
     * @param msg
     */
    fun e(msg: String) {
        if (isDebug) {
            Log.e(TAG, msg)
        }
    }
}