package com.wisdplat.hmi.fragment.cararea

import android.view.View
import androidx.core.content.ContextCompat
import com.wisdplat.hmi.R
import com.wisdplat.hmi.base.BaseFragment
import com.wisdplat.hmi.databinding.FragmentAirBinding
import com.wisdplat.hmi.view.WheelView
import com.wisdplat.hmi.viewmodel.AirViewModel

/**
 *
@author zoushunhua
@Email zouhs@foxmail.com
@create 2022-07-29 16:53
 *
 */
class AirFragment : BaseFragment<FragmentAirBinding, AirViewModel>() {


    companion object {
        private val TAG = AirFragment::class.java.simpleName
    }

    private fun getNumbers(): List<String> {
        val list = arrayListOf<String>()
        for (i in 17..32) {
            list.add("$i°")
        }
        return list
    }

    override fun FragmentAirBinding.initBinding() {
        mBinding.wv.setItems(getNumbers())
        mBinding.wv.onWheelViewListener = object : WheelView.OnWheelViewListener() {
            override fun onSelected(index: Int, item: String?) {
            }
        }
        //设置关闭点击事件
        mBinding.imageviewSwitch.setOnClickListener {
            change(it)
        }
        //设置ACC点击事件
        mBinding.imageviewAc.setOnClickListener {
            change(it)
        }
        //设置ALC点击事件
        mBinding.imageviewLoop.setOnClickListener {
            change(it)
        }
    }

    private fun change(view: View) {
        //根据选择切换图标颜色
        when (view) {
            mBinding.imageviewSwitch -> {
                if (mBinding.imageviewSwitch.isSelected) {
                    mBinding.imageviewSwitch.background =
                        ContextCompat.getDrawable(
                            requireContext(),
                            (R.drawable.ic_fun_switch)
                        )
                } else {
                    mBinding.imageviewSwitch.background =
                        ContextCompat.getDrawable(
                            requireContext(),
                            (R.drawable.ic_fun_switch_grey)
                        )
                }
                mBinding.imageviewSwitch.isSelected = !mBinding.imageviewSwitch.isSelected
            }
            mBinding.imageviewAc -> {
                if (mBinding.imageviewAc.isSelected) {
                    mBinding.imageviewAc.background =
                        ContextCompat.getDrawable(
                            requireContext(),
                            (R.drawable.fun_air_ac)
                        )
                } else {
                    mBinding.imageviewAc.background =
                        ContextCompat.getDrawable(
                            requireContext(),
                            (R.drawable.fun_air_ac_blue)
                        )
                }
                mBinding.imageviewAc.isSelected = !mBinding.imageviewAc.isSelected
            }
            mBinding.imageviewLoop -> {
                if (mBinding.imageviewLoop.isSelected) {
                    mBinding.imageviewLoop.background =
                        ContextCompat.getDrawable(
                            requireContext(),
                            (R.drawable.fun_air_loop)
                        )
                } else {
                    mBinding.imageviewLoop.background =
                        ContextCompat.getDrawable(
                            requireContext(),
                            (R.drawable.fun_air_loop_blue)
                        )
                }
                mBinding.imageviewLoop.isSelected = !mBinding.imageviewLoop.isSelected
            }
        }
    }

    override fun onStart() {
        super.onStart()
        mBinding.wv.setSelection(5)
    }
}


