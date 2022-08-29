package com.wisdplat.hmi.objects

import Point
import com.wisdplat.hmi.base.BaseModel
import com.wisdplat.hmi.data.VertexArray

class Line(
    var position: Point,
    val lineWidth: Float,
    val lineHeight: Float,
) : BaseModel() {
    init {
        val data = createLine(position,lineWidth, lineHeight)
        vertexArray = VertexArray(data.vertexData)
        drawList = data.drawCommands
    }



    fun createLine(
        position: Point,
        lineWidth: Float,
        lineHeight: Float
    ): GeneratedData {
        return ObjectBuilder().appendLine(position, lineWidth,lineHeight).build()
    }

}