package com.wisdplat.hmi.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import com.wisdplat.hmi.App.Companion.context
import com.wisdplat.hmi.R
import com.wisdplat.hmi.base.BaseViewModel
import com.wisdplat.hmi.bean.EngineerCarModel
import com.wisdplat.hmi.util.ext.Ext.notNull
import com.wisdplat.hmi.view.ViewBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
@author tanqianpeng
@email  tqp0507@qq.com
@create 2022-8-1 12:30
 */

class EngineerViewModel : BaseViewModel() {
    @SuppressLint("StaticFieldLeak")
    private lateinit var frameLayout: FrameLayout
    fun setFrameLayout(frameLayout: FrameLayout?) {
        if (frameLayout != null) {
            this.frameLayout = frameLayout
        }
    }

    var width = 100//小车宽100
    var height = 200//小车高200
    var carMap = mutableMapOf<Int, EngineerCarModel>()

    /**
     * 添加小车放入carMap
     *@param car 新加小车模型
     */
    fun addCar(car: EngineerCarModel): EngineerCarModel? {
        //判断小车是否在Map里面  如果没有在的话说明是新加入的小车 否则是已加入的小车-更新位置
        return if (!carMap.containsKey(car.id) || !isContainerScreen(car)) {
            carMap[car.id] = car
           // showCar(car, frameLayout)
            car
        } else {
            val oldCar = carMap[car.id]
            oldCar?.apply {
                tag = 1
                oldCar.x = car.x
                oldCar.y = car.y
            }
        }
    }

    /**
     * 显示小车
     * @param car 显示要显示的小车
     * @param flMain 帧布局的实例，作为显示小车的容器
     */
//    private fun showCar(car: EngineerCarModel, flMain: FrameLayout) {
//        val mp = carMap.containsKey(car.id)
//        if (mp) {
//            //当carModel (ImageView) 为空时,说明这辆车是新加入的车
//            if (car.carModel == null || !isContainerScreen(car)) {
//                val left = doubleToIntX(car.x)
//                val top = doubleToIntY(car.y)
//                val imageView = getImageView(left, top)
//                car.carModel = imageView
//                flMain.addView(imageView)
//            }
//        }
//    }

    /**
     * 移动小车
     * @param car 要移动的小车
     */
//    fun moveCar(car: EngineerCarModel) {
//        //小车第一次进入界面 oldX为null 小车显示位置为当前坐标位置 所以X方向位置偏移量需要为0 所以oldX应等于x
//        if (car.oldX == null) {
//            car.oldX = car.x
//        }
//        if (car.oldY == null) {
//            car.oldY = car.y
//        }
//        notNull(car, car.x, car.y, car.oldX, car.oldY) {
//            val left = car.oldX?.let { offsetX(it, car.x!!) }!!
//            car.oldX = car.x //更新oldX
//            Log.d("TAG", "moveCar:${car.oldX} ")
//            //小车第一次进入界面 oldY为null 小车显示位置为当前坐标位置 所以Y方向位置偏移量需要为0 所以oldY应等于y
//            val top = car.oldY?.let { offsetY(it, car.y!!) }!!
//            car.oldY = car.y//更新oldY
//            Log.d("TAG", "${car.oldY}")
//            car.carModel.let { image ->
//                viewModelScope.launch(Dispatchers.Main) {
//                    moveView(image!!, left, top)
//                }
//            }
//        }
//    }

    private fun offsetY(yOld: Double, yNew: Double): Int {
        val my = yNew.minus(yOld)
        return my.toInt()
    }

    private fun offsetX(oldX: Double, xNew: Double): Int {
        val mx = xNew.minus(oldX)
        return mx.toInt()
    }

    private fun doubleToIntX(x: Double?): Int {
        return x!!.toInt()
    }

    private fun doubleToIntY(y: Double?): Int {
        return y!!.toInt()
    }

    /**
     * 修改ImageView位置的方法
     * @param view 移动对象
     * @param rawX 小车在X轴移动的偏移量
     * @param rawY 小车在Y轴移动的偏移量
     */
    private fun moveView(view: View, rawX: Int, rawY: Int) {
        view.offsetLeftAndRight(rawX)
        view.offsetTopAndBottom(rawY)
    }

    /**
     * 创建小车图片 设置小车图片的宽高，离左边界和顶部的距离
     * @param left  图片到左边界距离
     * @param top   图片到顶部距离
     */
    private fun getImageView(left: Int, top: Int): ImageView {
        val imageView =//小车宽100 高200
            ViewBuilder.conBuilder(context, width, height).mar(left, top, 0, 0)
                .createImageView()
        imageView.background = ContextCompat.getDrawable(
            context,
            (R.drawable.park_img_car)
        )
        return imageView
    }

    /**
     * 判断小车是否在屏幕里
     * @param car 移动中的小车
     */
    private fun isContainerScreen(car: EngineerCarModel): Boolean {
        if (car.x == null || car.y == null) {
            return false
        }
        //小车宽100 高200
        //判断超过边界移除条件：向下行驶过下边界超过小车高一半；向上行驶过上边界超过小车高一半带一点（视觉效果更好）；向左行驶过左边界超过小车宽一半；向右行驶过右边界超过小车宽一半
        if (car.y!! >= frameLayout.height - 100 || car.y!! <= -110 || car.x!! <= -50 || car.x!! >= frameLayout.width - 50) {
            return false
        }
        return true
    }
}