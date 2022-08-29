package com.wisdplat.hmi.fragment.eng

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.wisdplat.hmi.AirGLSurfaceView
import com.wisdplat.hmi.base.BaseFragment
import com.wisdplat.hmi.bean.EngineerCarModel
import com.wisdplat.hmi.databinding.FragmentEngineerBinding

import com.wisdplat.hmi.repository.EngineerRepository
import com.wisdplat.hmi.viewmodel.EngineerViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *
@author tanqianpeng
@email  tqp0507@qq.com
@create 2022-8-1 12:30
 *
 */
class EngineerFragment : BaseFragment<FragmentEngineerBinding, EngineerViewModel>() {
    override fun providerVMClass(): Class<EngineerViewModel> = EngineerViewModel::class.java
    override fun FragmentEngineerBinding.initBinding() {
        val textView = mBinding.tvSpeed
        val shader: Shader = LinearGradient(
            0f, 0f, 0f, textView.paint.textSize,
            Color.GRAY, Color.WHITE, Shader.TileMode.CLAMP
        )
        textView.paint.shader = shader

       // viewModel.setFrameLayout(mBinding.flMain)
//        lifecycleScope.launch {
//            EngineerRepository.moveCar().collect { newCar ->
//                collectCar(newCar)
//                delay(100)
//            }
//        }
    }

    /**
     * 根据车辆状态执行操作
     */
//    private fun collectCar(newCar: EngineerCarModel) {
//        val car: EngineerCarModel?
//        if (isContainerScreen(newCar)) {
//            //当屏幕中还未拥有小车或小车还未移出边界时，添加或移动小车
//            car = viewModel.addCar(newCar)
//            if (car != null) {
//                viewModel.moveCar(car)
//            }
//        } else {
//            //当判断结果为false 即小车超过边界，从集合中移除小车数据，并且移除屏幕上的小车
//            val oldCar = viewModel.carMap.remove(newCar.id)
//            oldCar?.apply {
//                mBinding.flMain.removeView(carModel)
//            }
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.setFrameLayout(null)
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
        if (car.y!! >= mBinding.flMain.height - 100 || car.y!! <= -110 || car.x!! <= -50 || car.x!! >= mBinding.flMain.width - 50) {
            return false
        }
        return true
    }
}

