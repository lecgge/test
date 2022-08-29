package com.wisdplat.hmi.fragment.drivearea

import androidx.viewpager2.widget.ViewPager2
import com.wisdplat.hmi.adapter.ParkViewPagerAdapter
import com.wisdplat.hmi.adapter.ZoomOutPageTransformer
import com.wisdplat.hmi.base.BaseFragment
import com.wisdplat.hmi.databinding.FragmentParkInBinding
import com.wisdplat.hmi.util.LogUtil
import com.wisdplat.hmi.viewmodel.ParkViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *
@author xuleyu
@Email xuleyumail@gmail.com
@create 2022-07-29 15:01
 *
 */
class ParkInFragment : BaseFragment<FragmentParkInBinding, ParkViewModel>() {

    private val binding get() = mBinding

    override fun FragmentParkInBinding.initBinding() {
        val list = arrayListOf(
            ParkSearchFragment(),
            ParkStartFragment(),
            ParkFinishFragment()
        )
        //设置viewpager 切换动画
        binding.parkinViewpager.setPageTransformer(ZoomOutPageTransformer())
        binding.parkinViewpager.adapter =
            ParkViewPagerAdapter(childFragmentManager, lifecycle, list)
        binding.parkinViewpager.isUserInputEnabled = false
        //viewpager页面切换监听事件
        binding.parkinViewpager.registerOnPageChangeCallback(
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

            })
    }

    override fun onResume() {
        super.onResume()
        MainScope().launch {
            binding.parkinViewpager.setCurrentItem(0, false)
            delay(2000)
            binding.parkinViewpager.setCurrentItem(1, false)
            delay(2000)
            binding.parkinViewpager.setCurrentItem(2, false)
        }
    }
}
