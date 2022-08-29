package com.wisdplat.hmi.fragment.meter

import android.graphics.Color
import androidx.lifecycle.lifecycleScope
import com.wisdplat.hmi.base.BaseFragment
import com.wisdplat.hmi.bean.MeterCarModel
import com.wisdplat.hmi.databinding.FragmentMeterBinding
import com.wisdplat.hmi.repository.MeterRepository
import com.wisdplat.hmi.viewmodel.MeterViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *
@author tanqianpeng
@email  tqp0507@qq.com
@create 2022-8-1 12:30
 *
 */
class MeterFragment : BaseFragment<FragmentMeterBinding, MeterViewModel>() {
    override fun providerVMClass(): Class<MeterViewModel> = MeterViewModel::class.java
    override fun FragmentMeterBinding.initBinding() {
        viewModel.timeViewModel()
        mBinding.meterViewModel = viewModel //时间ui刷新
        //指示灯颜色变化
        viewModel.ledViewModel()
        viewModel.light.observe(viewLifecycleOwner) {
            if (it == 1) {
                mBinding.sideMarkerLamp.setColorFilter(Color.GREEN)
                mBinding.highBeam.setColorFilter(Color.GREEN)
                mBinding.dippedHeadlight.setColorFilter(Color.GREEN)
                mBinding.rearFogLamp.setColorFilter(Color.GREEN)
                mBinding.frontFogLamp.setColorFilter(Color.GREEN)
                mBinding.parking.setColorFilter(Color.RED)
                mBinding.waterTemperature.setColorFilter(Color.YELLOW)
                mBinding.engineCover.setColorFilter(Color.RED)
                mBinding.engineOil.setColorFilter(Color.RED)
                mBinding.trunk.setColorFilter(Color.RED)
                mBinding.carDoor.setColorFilter(Color.RED)
            } else {
                mBinding.sideMarkerLamp.setColorFilter(Color.GRAY)
                mBinding.highBeam.setColorFilter(Color.GRAY)
                mBinding.dippedHeadlight.setColorFilter(Color.GRAY)
                mBinding.rearFogLamp.setColorFilter(Color.GRAY)
                mBinding.frontFogLamp.setColorFilter(Color.GRAY)
                mBinding.parking.setColorFilter(Color.GRAY)
                mBinding.waterTemperature.setColorFilter(Color.GRAY)
                mBinding.engineCover.setColorFilter(Color.GRAY)
                mBinding.engineOil.setColorFilter(Color.GRAY)
                mBinding.trunk.setColorFilter(Color.GRAY)
                mBinding.carDoor.setColorFilter(Color.GRAY)
            }
        }
        //档位切换颜色变化
        viewModel.gearViewModel()
        mBinding.showColor = Color.GREEN
        mBinding.noShowColor = Color.GRAY
        //时速转速盘变化
        viewModel.rmpViewModel()
        viewModel.speed.observe(viewLifecycleOwner) {
            mBinding.speedDisplay.setCompleteDegree(it.toFloat())
        }
        viewModel.rev.observe(viewLifecycleOwner) {
            mBinding.revolutionDisplay.setCompleteDegree(it.toFloat())
        }
        //小车
        viewModel.setFrameLayout(mBinding.flMeterMain)
        lifecycleScope.launch {
            MeterRepository.moveCar().collect { newCar ->
                collectCar(newCar)
                delay(100)
            }
        }
    }

    /**
     * 根据车辆状态执行操作
     */
    private fun collectCar(newCar: MeterCarModel) {
        val car: MeterCarModel?
        if (isContainerScreen(newCar)) {
            //当屏幕中还未拥有小车或小车还未移出边界时，添加或移动小车
            car = viewModel.addCar(newCar)
            if (car != null) {
                viewModel.moveCar(car)
            }
        } else {
            //当判断结果为false 即小车超过边界，从集合中移除小车数据，并且移除屏幕上的小车
            val oldCar = viewModel.carMap.remove(newCar.id)
            oldCar?.apply {
                mBinding.flMeterMain.removeView(carModel)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.setFrameLayout(null)
    }

    /**
     * 判断小车是否在屏幕里
     * @param car 移动中的小车
     */
    private fun isContainerScreen(car: MeterCarModel): Boolean {
        if (car.x == null || car.y == null) {
            return false
        }
        //小车宽100 高200
        //判断超过边界移除条件：向下行驶过下边界超过小车高一半；向上行驶过上边界超过小车高一半带一点（视觉效果更好）；向左行驶过左边界超过小车宽一半；向右行驶过右边界超过小车宽一半
        if (car.y!! >= mBinding.flMeterMain.height - 100 || car.y!! <= -110 || car.x!! <= -50 || car.x!! >= mBinding.flMeterMain.width - 50) {
            return false
        }
        return true
    }
}