package com.wisdplat.hmi.base

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PixelFormat
import com.wisdplat.hmi.AirGLSurfaceView
import com.wisdplat.hmi.AirHockeyRenderer1
import com.wisdplat.hmi.base.BaseRenderer

@SuppressLint("StaticFieldLeak")
object GLESHelper {
    lateinit var airGLSurfaceView: AirGLSurfaceView
    lateinit var renderer: BaseRenderer


    fun init(context: Context,renderer: BaseRenderer) {
        this.renderer = renderer
        airGLSurfaceView = AirGLSurfaceView(renderer, context)
    }

    @SuppressLint("ClickableViewAccessibility")
    fun setOnTouchListener(onTouchListener: OnTouchListener) {
        airGLSurfaceView.setOnTouchListener(onTouchListener)
    }
}