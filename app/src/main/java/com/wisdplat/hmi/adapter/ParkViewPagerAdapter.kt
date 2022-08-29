package com.wisdplat.hmi.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 *
@author xuleyu
@Email xuleyumail@gmail.com
@create 2022-07-29 15:01
 *
 */
class ParkViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val fragmentList: List<Fragment>
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int): Fragment {

        return fragmentList[position]
    }
}
