package com.wisdplat.hmi.bean

import android.widget.ImageView

/**
 *
@author zoushunhua
@Email zouhs@foxmail.com
@create 2022-08-11 10:18
 *
 */

//指示灯
data class MeterLedModel constructor(
    var tag: Int, //0灭 1亮
)

//档位
data class MeterDriveModel constructor(
    var id: Int,
    var tag: Int // 0 1
)

//时速转速盘
data class MeterPanelModel constructor(
    var speed: Float,
    var rev: Float
)

data class MeterCarModel @JvmOverloads constructor(
    val id: Int,
    var tag: Int, //0，1 0代表新加入的Car，1代表之前存在且坐标更新了的Car
    var x: Double? = null,
    var y: Double? = null,
    var carModel: ImageView? = null

) {
    var oldX: Double? = null
    var oldY: Double? = null
}