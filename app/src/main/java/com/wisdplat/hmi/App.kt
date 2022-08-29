package com.wisdplat.hmi

import android.app.Application
import android.content.Context
import com.wisdplat.hmi.util.LogUtil
import com.wisdplat.hmi.util.ext.Ext.isDebugVersion

/**
 *
@author tanqianpeng
@email  tqp0507@qq.com
@create 2022-8-1 12:30
 *
 */
class App : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        LogUtil.isDebug = isDebugVersion()
    }
}