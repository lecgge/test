package com.wisdplat.hmi.objects
import Point
import android.content.Context
import android.opengl.GLES20.*
import android.util.Log
import androidx.annotation.DrawableRes

import com.wisdplat.hmi.R
import com.wisdplat.hmi.base.BaseTextureModel
import com.wisdplat.hmi.base.ModelDrawInfo

class Car(
    id : Int,
    context: Context,
    position: Point,
    @DrawableRes resId: Int=R.drawable.car ,
    isPerspective: Boolean=false
) : BaseTextureModel(id, context, resId, position) {

    override fun draw() {
        glEnable(GL_BLEND)
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)
        glDrawArrays(GL_TRIANGLE_FAN, 0, 6)
    }

    override fun setVertexData(): FloatArray {
        return floatArrayOf(
            // 数据顺序: X, Y, S, T
            // 三角形扇形
            0f, 0f, 0.5f, 0.5f,
            -0.3f, -0.3f, 0f, 0.9f,
            0.3f, -0.3f, 1f, 0.9f,
            0.3f, 0.3f, 1f, 0.1f,
            -0.3f, 0.3f, 0f, 0.1f,
            -0.3f, -0.3f, 0f, 0.9f
        )
    }

    override fun setDrawInfo() : ModelDrawInfo {
        return object : ModelDrawInfo{
            override val shaper: Int
                get() = GL_TRIANGLE_FAN
            override val first: Int
                get() = 0
            override val count: Int
                get() = 6

        }
    }
}
