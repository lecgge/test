package com.wisdplat.hmi.fragment.drivearea

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import com.wisdplat.hmi.R
import com.wisdplat.hmi.base.BaseFragment
import com.wisdplat.hmi.databinding.FragmentParkOutBinding
import com.wisdplat.hmi.viewmodel.ParkViewModel

/**
 *
@author xuleyu
@Email xuleyumail@gmail.com
@create 2022-07-29 15:01
 *
 */
class ParkOutFragment : BaseFragment<FragmentParkOutBinding, ParkViewModel>() {
    val binding get() = mBinding

    private fun change(view: View) {
        //根据选择切换图标和文字的颜色
        when (view) {
            binding.leftParkOut -> {
                binding.leftArrow.setColorFilter(Color.parseColor("#3688FF"))
                binding.leftParkOutText.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        (R.color.blue_60)
                    )
                )

                binding.rightArrow.setColorFilter(Color.parseColor("#BBBBBB"))
                binding.rightParkOutText.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        (R.color.gray_200)
                    )
                )
            }

            binding.rightParkOut -> {
                binding.rightArrow.setColorFilter(Color.parseColor("#3688FF"))
                binding.rightParkOutText.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        (R.color.blue_60)
                    )
                )

                binding.leftArrow.setColorFilter(Color.parseColor("#BBBBBB"))
                binding.leftParkOutText.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        (R.color.gray_200)
                    )
                )
            }
        }
    }

    override fun FragmentParkOutBinding.initBinding() {
        //设置向左泊车图标点击事件
        binding.leftParkOut.setOnClickListener {
            change(it)
        }
        //设置向右泊车图标点击事件
        binding.rightParkOut.setOnClickListener {
            change(it)
        }
    }

}
