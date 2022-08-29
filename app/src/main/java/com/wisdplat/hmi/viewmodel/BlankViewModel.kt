package com.wisdplat.hmi.viewmodel

import android.annotation.SuppressLint
import android.widget.FrameLayout
import com.wisdplat.hmi.App.Companion.context
import com.wisdplat.hmi.base.BaseRenderer
import com.wisdplat.hmi.base.BaseTextureModel
import com.wisdplat.hmi.base.BaseViewModel
import com.wisdplat.hmi.repository.EngineerRepository.moveTextureModel


/**
 *@Author QiYuZhen
 *
 */
class BlankViewModel : BaseViewModel(){

    @SuppressLint("StaticFieldLeak")
    private lateinit var frameLayout: FrameLayout
    fun setFrameLayout(frameLayout: FrameLayout?) {
        if (frameLayout != null) {
            this.frameLayout = frameLayout
        }
    }
    /**
     * 模型数组，包含创建的所有TextureModel
     */
    val modelMap = mutableMapOf<Int, BaseTextureModel>()
    /**
     * 添加小车放入carMap
     *@param car 新加小车模型
     */
    fun addTextureModel(model:BaseTextureModel) {
        if (modelMap.containsKey(model.id)) {
            moveTextureModel(model)
        } else {
            modelMap.put(model.id,model)
        }
    }
   fun moveTextureModel(model: BaseTextureModel) {
        if (modelMap.containsKey(model.id)) {
            modelMap.get(model.id)?.position = model.position
        } else {
            addTextureModel(model)
        }
    }




//    fun addCar(car: EngineerCarModel): EngineerCarModel? {
//        //判断小车是否在Map里面  如果没有在的话说明是新加入的小车 否则是已加入的小车-更新位置
//        return if (!modelMap.containsKey(car.id) ) {
//            modelMap[car.id] = car
//            // showCar(car, frameLayout)
//            car
//        } else {
//            val oldCar = modelMap[car.id]
//            oldCar?.apply {
//                tag = 1
//                oldCar.x = car.x
//                oldCar.y = car.y
//            }
//        }
//    }
}