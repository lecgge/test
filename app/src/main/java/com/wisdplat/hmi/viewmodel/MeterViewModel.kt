package com.wisdplat.hmi.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wisdplat.hmi.App
import com.wisdplat.hmi.R
import com.wisdplat.hmi.base.BaseViewModel
import com.wisdplat.hmi.bean.MeterCarModel
import com.wisdplat.hmi.bean.MeterDriveModel
import com.wisdplat.hmi.repository.MeterRepository
import com.wisdplat.hmi.util.ext.Ext.notNull
import com.wisdplat.hmi.view.ViewBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
@author tanqianpeng
@email  tqp0507@qq.com
@create 2022-8-1 12:30
 */

@SuppressLint("SimpleDateFormat")
class MeterViewModel : BaseViewModel() {

    private var _time = MutableLiveData<String>()
    val time get() = _time
    var width = 100//小车宽100
    var height = 200//小车高200


    private var _light = MutableLiveData<Int>()
    val light get() = _light

    private var _meterDriveModel = MutableLiveData<MeterDriveModel>()
    val meterDriveModel get() = _meterDriveModel

    private var _speed = MutableLiveData<Float>()
    val speed get() = _speed

    private var _rotate = MutableLiveData<Float>()
    val rev get() = _rotate

    init {//初始化时分秒
        val simpleDateFormat = SimpleDateFormat(" HH:mm:ss ")
        val date = Date(System.currentTimeMillis())
        time.value = simpleDateFormat.format(date)
    }

    fun timeViewModel() {
        viewModelScope.launch(Dispatchers.Main) {
            MeterRepository.changeTime().collect {
                _time.value = it
            }
        }
    }

    fun ledViewModel() {
        viewModelScope.launch {
            MeterRepository.changeLed().collect {
                _light.value = it.tag
            }
        }
    }

    fun gearViewModel() {
        viewModelScope.launch {
            MeterRepository.changeGear().collect {
                _meterDriveModel.value = it
            }
        }
    }

    fun rmpViewModel() {
        viewModelScope.launch {
            MeterRepository.changePanel().collect {
                _speed.value = it.speed
                _rotate.value = it.rev

            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private lateinit var frameLayout: FrameLayout
    fun setFrameLayout(frameLayout: FrameLayout?) {
        if (frameLayout != null) {
            this.frameLayout = frameLayout
        }
    }

    var carMap = mutableMapOf<Int, MeterCarModel>()

    /**
     * 添加小车放入carMap
     *@param car 新加小车模型
     */
    fun addCar(car: MeterCarModel): MeterCarModel? {
        //判断小车是否在Map里面  如果没有在的话说明是新加入的小车 否则是已加入的小车-更新位置
        return if (!carMap.containsKey(car.id)) {
            carMap[car.id] = car
            showCar(car, frameLayout)
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
    private fun showCar(car: MeterCarModel, flMain: FrameLayout) {
        val mp = carMap.containsKey(car.id)
        if (mp) {
            //当carModel (ImageView) 为空时,说明这辆车是新加入的车
            if (car.carModel == null) {
                val left = doubleToIntX(car.x)
                val top = doubleToIntY(car.y)
                val imageView = getImageView(left, top)
                car.carModel = imageView
                flMain.addView(imageView)
            }
        }
    }

    /**
     * 移动小车
     * @param car 要移动的小车
     */
    fun moveCar(car: MeterCarModel) {
        //小车第一次进入界面 oldX为null 小车显示位置为当前坐标位置 所以X方向位置偏移量需要为0 所以oldX应等于x
        if (car.oldX == null) {
            car.oldX = car.x
        }
        if (car.oldY == null) {
            car.oldY = car.y
        }
        notNull(car, car.x, car.y, car.oldX, car.oldY) {
            val left = car.oldX?.let { offsetX(it, car.x!!) }!!
            car.oldX = car.x //更新oldX
            Log.d("TAG", "moveCar:${car.oldX} ")
            //小车第一次进入界面 oldY为null 小车显示位置为当前坐标位置 所以Y方向位置偏移量需要为0 所以oldY应等于y
            val top = car.oldY?.let { offsetY(it, car.y!!) }!!
            car.oldY = car.y//更新oldY
            Log.d("TAG", "${car.oldY}")
            car.carModel.let { image ->
                viewModelScope.launch(Dispatchers.Main) {
                    moveView(image!!, left, top)
                }
            }
        }
    }

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
            ViewBuilder.conBuilder(App.context, width, height).mar(left, top, 0, 0)
                .createImageView()
        imageView.background = ContextCompat.getDrawable(
            App.context,
            (R.drawable.park_img_car)
        )
        return imageView
    }
}
