package com.wisdplat.hmi

import android.content.Context
import android.graphics.PixelFormat
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import com.wisdplat.hmi.base.BaseRenderer

/**
 *@Author QiYuZhen
 *@Date 0808 12:24
 */
class AirGLSurfaceView @JvmOverloads constructor(
    var renderer: BaseRenderer,
    context: Context, attrs:AttributeSet?=null
): GLSurfaceView(context,attrs){



    init {
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        setZOrderOnTop(true);
        setEGLContextClientVersion(2)
        setRenderer(renderer)
    }
    override fun onResume() {
        super.onResume()
    }
}