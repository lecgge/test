package com.wisdplat.hmi.fragment.drivearea

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.wisdplat.hmi.R
import com.wisdplat.hmi.adapter.ParkViewPagerAdapter
import com.wisdplat.hmi.adapter.ZoomOutPageTransformer
import com.wisdplat.hmi.base.BaseFragment
import com.wisdplat.hmi.databinding.FragmentDriveAreaBinding
import com.wisdplat.hmi.util.LogUtil
import com.wisdplat.hmi.viewmodel.FunctionViewModel

/**
 *
@author zoushunhua
@Email zouhs@foxmail.com
@create 2022-07-29 16:53
 *
 */
class DriveAreaFragment : BaseFragment<FragmentDriveAreaBinding, FunctionViewModel>() {
    @SuppressLint("ResourceAsColor")
    override fun FragmentDriveAreaBinding.initBinding() {
        //初始化Fragment list
        val list = arrayListOf(
            DriveFragment(),
            ParkInFragment(),
            ParkOutFragment()
        )
        //设置viewpager 切换动画
        mBinding.funViewpager.setPageTransformer(ZoomOutPageTransformer())
        mBinding.funViewpager.adapter =
            ParkViewPagerAdapter(childFragmentManager, lifecycle, list)
        mBinding.funViewpager.isUserInputEnabled = false
        //viewpager页面切换监听事件
        mBinding.funViewpager.registerOnPageChangeCallback(
            object :
                ViewPager2.OnPageChangeCallback() {

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    LogUtil.d("$position,$positionOffset,$positionOffsetPixels")
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    change(position)
                }

            })

        //设置顶部 两个textview 的点击事件，点击切换页面，模拟导航栏，
        mBinding.driveText.setOnClickListener {

            change(mBinding.driveText.id)
        }

        mBinding.parkInText.setOnClickListener {
            change(mBinding.parkInText.id)
        }
        mBinding.parkOutText.setOnClickListener {
            change(mBinding.parkOutText.id)
        }

    }


    /**
     * @param position  页面切换后的目标页面，可以是id，也可以说viewpager中的position
     */
    private fun change(position: Int) {
        when (position) {
            R.id.drive_text, 0 -> {
                //设置文字的颜色，及文字下方导航条的显示
                mBinding.driveLine.visibility = View.VISIBLE
                mBinding.driveText.setTextColor(Color.WHITE)
                mBinding.parkedLine.visibility = View.GONE
                mBinding.parkInText.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.gray_60
                    )
                )
                mBinding.parkOutLine.visibility = View.GONE
                mBinding.parkOutText.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.gray_60
                    )
                )
                //切换页面
                mBinding.funViewpager.currentItem = 0
            }

            R.id.park_in_text, 1 -> {
                //设置文字的颜色，及文字下方导航条的显示
                mBinding.parkedLine.visibility = View.VISIBLE
                mBinding.parkInText.setTextColor(Color.WHITE)
                mBinding.driveLine.visibility = View.GONE
                mBinding.driveText.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.gray_60
                    )
                )
                mBinding.parkOutLine.visibility = View.GONE
                mBinding.parkOutText.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.gray_60
                    )
                )
                //切换页面
                mBinding.funViewpager.currentItem = 1
            }

            R.id.park_out_text, 2 -> {
                //设置文字的颜色，及文字下方导航条的显示
                mBinding.parkOutLine.visibility = View.VISIBLE
                mBinding.parkOutText.setTextColor(Color.WHITE)
                mBinding.driveLine.visibility = View.GONE
                mBinding.driveText.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.gray_60
                    )
                )
                mBinding.parkedLine.visibility = View.GONE
                mBinding.parkInText.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.gray_60
                    )
                )
                //切换页面
                mBinding.funViewpager.currentItem = 2
            }
        }
    }
}
