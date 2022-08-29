package com.wisdplat.hmi.base



import com.wisdplat.hmi.data.VertexArray
import com.wisdplat.hmi.objects.DrawCommand
import com.wisdplat.hmi.programs.ColorShaderProgram

abstract class BaseModel : IModel {
    lateinit var vertexArray: VertexArray
    lateinit var drawList: List<DrawCommand>

    override fun bindData(colorShaderProgram: ColorShaderProgram){
        vertexArray.setVertexAttribPointer(
            dataOffset = 0,
            attributeLocation =colorShaderProgram.aPositionLocation,
            componentCount = POSITION_COMPONENT_COUNT,
            stride = 0

        )
    }

    companion object {
        private const val POSITION_COMPONENT_COUNT = 3
    }

    override fun draw() {
        drawList.forEach {
            it.draw()
        }
    }
}