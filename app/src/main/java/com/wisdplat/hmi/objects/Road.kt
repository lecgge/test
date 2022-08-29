package com.wisdplat.hmi.objects

import Point
import com.wisdplat.hmi.data.VertexArray
import com.wisdplat.hmi.programs.ColorShaderProgram

/**
 *@Author QiYuZhen
 *
 */
class Road ( var position: Point,
val roadWidth: Float,
val roadHeight: Float,
) {
    private var vertexArray: VertexArray
    private var drawList: List<DrawCommand>

    init {
        val data = createRoad(position,roadWidth, roadHeight)
        vertexArray = VertexArray(data.vertexData)
        drawList = data.drawCommands
    }

    fun bindData(colorShaderProgram: ColorShaderProgram){
        vertexArray.setVertexAttribPointer(
            dataOffset = 0,
            attributeLocation =colorShaderProgram.aPositionLocation,
            componentCount = POSITION_COMPONENT_COUNT,
            stride = 0
        )
    }

    fun draw() {
        drawList.forEach {
            it.draw()
        }
    }

    fun createRoad(
        position: Point,
        roadWidth: Float,
        roadHeight: Float
    ): GeneratedData {
        return ObjectBuilder().appendRoad(position, roadWidth,roadHeight).build()
    }

    companion object {
        private const val POSITION_COMPONENT_COUNT = 3
    }
}