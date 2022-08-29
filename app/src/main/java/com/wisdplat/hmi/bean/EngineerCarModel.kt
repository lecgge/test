package com.wisdplat.hmi.bean

import android.widget.ImageView
import com.wisdplat.hmi.base.BaseTextureModel

/**
 *
@author zoushunhua
@Email zouhs@foxmail.com
@create 2022-08-8 10:15
 *
 */
data class EngineerCarModel @JvmOverloads constructor(
    val id: Int,
    var tag: Int, //0，1 0代表新加入的Car，1代表之前存在且坐标更新了的Car
    var x: Double? = null,
    var y: Double? = null,
   var carModel: ImageView? = null
//var carModel: BaseTextureModel

) {
    var oldX: Double? = null
    var oldY: Double? = null
}