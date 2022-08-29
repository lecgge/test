package com.wisdplat.hmi.base


import com.wisdplat.hmi.programs.ColorShaderProgram


interface IModel {

    fun bindData(colorShaderProgram: ColorShaderProgram)
    fun draw()
}