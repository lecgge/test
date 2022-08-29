package com.wisdplat.hmi


import Point
import androidx.lifecycle.lifecycleScope
import com.wisdplat.hmi.base.BaseFragment
import com.wisdplat.hmi.base.BaseTextureModel
import com.wisdplat.hmi.base.GLESHelper
import com.wisdplat.hmi.databinding.FragmentBlankBinding
import com.wisdplat.hmi.objects.Car
import com.wisdplat.hmi.repository.EngineerRepository
import com.wisdplat.hmi.viewmodel.BlankViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BlankFragment : BaseFragment<FragmentBlankBinding, BlankViewModel>() {
    var y = 0f
    override fun FragmentBlankBinding.initBinding() {

        GLESHelper.init(context = App.context, renderer = AirHockeyRenderer1(App.context))
        val carModel = Car(1,App.context, Point(-0.4f, 0f, 0f),R.drawable.car)
        collectCar(carModel)

        mBinding.flMain.addView(GLESHelper.airGLSurfaceView)

//
//        lifecycleScope.launch(Dispatchers.IO) {
//            EngineerRepository.moveTextureModel().collect { newCar ->
//                collectCar(newCar)
//                //  GLESHelper.renderer.drawTextureModel(newCar)
//            }
//
//        }


    }


    /**
     * 模型数组，包含创建的所有TextureModel
     */
    open val modelMap = mutableMapOf<Int, BaseTextureModel>()

    /**
     * 根据车辆状态执行操作
     */
    fun collectCar(newCar: BaseTextureModel) {

        GLESHelper.renderer.addTextureModel(
            newCar
        )
//        modelMap.forEach { i, baseTextureModel ->
//            GLESHelper.renderer.drawTextureModel(newCar)
//        }
//        while (true) {
//            y += 0.01f
//            GLESHelper.renderer.moveTextureModel(
//                newCar
//            )
//        }


    }

    override fun onResume() {
        super.onResume()
        GLESHelper.airGLSurfaceView.onResume()
    }

    override fun onPause() {
        super.onPause()
        GLESHelper.airGLSurfaceView.onPause()
    }

}