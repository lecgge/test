package com.wisdplat.hmi.base

import Point
import android.content.Context
import android.opengl.ETC1Util.loadTexture
import android.opengl.GLES20
import android.util.Log
import androidx.annotation.DrawableRes
import com.wisdplat.hmi.App
import com.wisdplat.hmi.data.Constants
import com.wisdplat.hmi.data.VertexArray
import com.wisdplat.hmi.programs.TextureShaderProgram
import com.wisdplat.hmi.util.loadTexture

abstract class BaseTextureModel(
    val id: Int,
    val context: Context,
    @DrawableRes
    val resId: Int,
    var position: Point
) : ITextureModel{

    //是否需要使用透视投影矩阵
    var isPerspective = false

    constructor(
        id: Int,
        context: Context,
        @DrawableRes
        resId: Int,
        position: Point,
        isPerspective:Boolean
    ) : this(id, context, resId, position){
        this.isPerspective = isPerspective
    }

    val vertexArray: VertexArray = VertexArray(vertexData)
    var texture: Int = 0
    init {
        texture = context.loadTexture(resId)
        Log.d("Car", "texture: $texture")
    }
    companion object {
        var POSITION_COMPONENT_COUNT = 2
        var TEXTURE_COORDINATES_COMPONENT_COUNT = 2
        var STRIDE: Int = (POSITION_COMPONENT_COUNT
                + TEXTURE_COORDINATES_COMPONENT_COUNT) * Constants.BYTES_PER_FLOAT

    }

    override fun bindData(textureShaderProgram: TextureShaderProgram) {
        // 获取程序对象的变量的位置，并绑定vertexArray包含的数据
        vertexArray.setVertexAttribPointer(
            dataOffset = 0,
            attributeLocation = textureShaderProgram.aPositionLocation,
            componentCount = POSITION_COMPONENT_COUNT,
            stride = STRIDE
        )
        vertexArray.setVertexAttribPointer(
            dataOffset = POSITION_COMPONENT_COUNT,
            attributeLocation = textureShaderProgram.aTextureCoordinatesLocation,
            componentCount = TEXTURE_COORDINATES_COMPONENT_COUNT,
            stride = STRIDE
        )
    }

    override fun draw(drawShaper: Int, first: Int, count: Int) {
        GLES20.glEnable(GLES20.GL_BLEND)
        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA)
        GLES20.glDrawArrays(drawShaper, first, count)
    }


}