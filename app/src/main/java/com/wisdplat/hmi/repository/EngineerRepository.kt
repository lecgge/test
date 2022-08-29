package com.wisdplat.hmi.repository

import Point
import android.widget.ImageView
import com.wisdplat.hmi.App
import com.wisdplat.hmi.R
import com.wisdplat.hmi.base.BaseTextureModel
import com.wisdplat.hmi.base.ITextureModel
import com.wisdplat.hmi.bean.EngineerCarModel
import com.wisdplat.hmi.bean.EngineerSignals
import com.wisdplat.hmi.objects.Car
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 *
@author zoushunhua
@Email zouhs@foxmail.com
@create 2022-08-8 16:18
 *
 */
object EngineerRepository {
//    fun moveCar(): Flow<EngineerCarModel> = flow {
//        var y = 10
//        var x = 10
//        for (i in 0..30) {
//            y -= 5
//            x += 5
//            val carModel =
//                EngineerCarModel(0, 0, (x).toDouble(), (y).toDouble())
//            emit(carModel)
//        }
//    }.flowOn(Dispatchers.IO)

fun moveTextureModel(): Flow<BaseTextureModel> = flow {

            var y=0f
        val carModel =Car(1,App.context, Point(-0.4f, y , 0f),R.drawable.car)

         emit(carModel)


    }.flowOn(Dispatchers.IO)

    fun signals(): Flow<EngineerSignals> = flow {
        var test = EngineerSignals("功能TJA", "TJA 02", R.drawable.engineer_icon_tja_car, 0)
        emit(test)
        var dest = EngineerSignals("功能ACC", "ACC 03", R.drawable.engineer_icon_acc_car, 0)
        emit(dest)
        kotlinx.coroutines.delay(500)
        dest = EngineerSignals("功能ACC", "ACC 03", R.drawable.engineer_icon_acc_car_green, 1)
        emit(dest)

        var cest = EngineerSignals("功能FCW", "FCW 01", R.drawable.engineer_icon_fcw_car, 0)
        emit(cest)
        kotlinx.coroutines.delay(500)
        cest = EngineerSignals("功能FCW", "FCW 01", R.drawable.engineer_icon_fcw_car_yellow, 1)
        emit(cest)

        var best = EngineerSignals("功能AEB", "AEB 01", R.drawable.engineer_icon_aeb_car, 0)
        emit(best)
        kotlinx.coroutines.delay(500)
        best = EngineerSignals("功能AEB", "AEB 01", R.drawable.engineer_icon_aeb_car_green, 1)
        emit(best)

        var lest = EngineerSignals("功能DMS", "DMS 03", R.drawable.engineer_icon_dms_car, 0)
        emit(lest)
        kotlinx.coroutines.delay(500)
        test = EngineerSignals("功能TJA", "TJA 02", R.drawable.engineer_icon_tja_car_yellow, 1)
        emit(test)
        lest = EngineerSignals("功能DMS", "DMS 03", R.drawable.engineer_icon_dms_car_yellow, 1)
        emit(lest)

        var rest = EngineerSignals("功能RDA", "RDA 02", R.drawable.engineer_icon_rda_car, 0)
        emit(rest)
        kotlinx.coroutines.delay(500)
        rest = EngineerSignals("功能RDA", "RDA 02", R.drawable.engineer_icon_rda_car_yellow, 1)
        emit(rest)
    }.flowOn(Dispatchers.IO)
}