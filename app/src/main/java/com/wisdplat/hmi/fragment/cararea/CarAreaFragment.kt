package com.wisdplat.hmi.fragment.cararea

import android.graphics.Color
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.wisdplat.hmi.R
import com.wisdplat.hmi.adapter.ParkViewPagerAdapter
import com.wisdplat.hmi.adapter.ZoomOutPageTransformer
import com.wisdplat.hmi.base.BaseFragment
import com.wisdplat.hmi.databinding.FragmentCarAreaBinding
import com.wisdplat.hmi.viewmodel.ParkViewModel

/**
 *
@author xuleyu
@Email xuleyumail@gmail.com
@create 2022-07-29 15:01
 *
 */
class CarAreaFragment : BaseFragment<FragmentCarAreaBinding, ParkViewModel>() {

    private val binding get() = mBinding


    /**
     * @param position  页面切换后的目标页面，可以是id，也可以说viewpager中的position
     */
    private fun change(position: Int) {
        when (position) {
            R.id.air_text, 0 -> {
                //设置文字的颜色，及文字下方导航条的显示
                binding.airLine.visibility = View.VISIBLE
                binding.airText.setTextColor(Color.WHITE)
                //切换页面
                binding.parkViewpager.currentItem = 0
            }
        }
    }

    override fun FragmentCarAreaBinding.initBinding() {

        //初始化Fragment list
        val list = arrayListOf(
            AirFragment()
        )
        //设置viewpager 切换动画
        binding.parkViewpager.setPageTransformer(ZoomOutPageTransformer())
        binding.parkViewpager.adapter =
            ParkViewPagerAdapter(childFragmentManager, lifecycle, list)
        binding.parkViewpager.isUserInputEnabled = false
        //viewpager页面切换监听事件
        binding.parkViewpager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                change(position)
            }

        })
        //设置顶部 两个textview 的点击事件，点击切换页面，模拟导航栏，
        binding.airText.setOnClickListener {

            change(binding.airText.id)
        }
    }
}
