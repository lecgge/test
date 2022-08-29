package com.wisdplat.hmi.repository

import android.annotation.SuppressLint
import com.wisdplat.hmi.bean.MeterCarModel
import com.wisdplat.hmi.bean.MeterDriveModel
import com.wisdplat.hmi.bean.MeterLedModel
import com.wisdplat.hmi.bean.MeterPanelModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.text.SimpleDateFormat
import java.util.*

/**
 *
@author zoushunhua
@Email zouhs@foxmail.com
@create 2022-08-11 10:18
 *
 */
object MeterRepository {
    //时间变化
    @SuppressLint("SimpleDateFormat")
    fun changeTime(): Flow<String> = flow {
        while (true) {
            val simpleDateFormat = SimpleDateFormat(" HH:mm:ss ")
            val date = Date(System.currentTimeMillis())
            val time = simpleDateFormat.format(date)
            emit(time)
            delay(1000)
        }
    }.flowOn(Dispatchers.IO)

    //指示灯变化
    fun changeLed(): Flow<MeterLedModel> = flow {
        val led = MeterLedModel(0)
        emit(led)
        delay(2000)
        emit(MeterLedModel(1))
        delay(2000)
        emit(led)
    }.flowOn(Dispatchers.IO)

    //档位切换
    fun changeGear(): Flow<MeterDriveModel> = flow {
        for (i in 1..4) {
            val test = MeterDriveModel(i, 0)
            emit(test)
            delay(500)
            test.tag = 1
            emit(test)
            delay(500)
            test.tag = 0
            emit(test)
        }
    }.flowOn(Dispatchers.IO)

    //仪表盘变化
    //大哥们 merge的时候看一眼吧 ！求求了
    fun changePanel(): Flow<MeterPanelModel> = flow {
        var i = 0f
        while (i < 10) {
            i++
            val speed = (0..100).random()
            val speedPanel = MeterPanelModel(speed.toFloat(), (speed.toFloat()) / 10)
            emit(speedPanel)
            delay(1000)
        }
    }.flowOn(Dispatchers.IO)

    fun moveCar(): Flow<MeterCarModel> = flow {
        var y = 10
        var x = 10
        for (i in 0..30) {
            y -= 5
//            x += 5
            val carModel =
                MeterCarModel(0, 0, (0).toDouble(), (y).toDouble())
            emit(carModel)
        }
    }.flowOn(Dispatchers.IO)
}