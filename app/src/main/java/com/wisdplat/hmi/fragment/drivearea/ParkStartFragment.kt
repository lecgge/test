package com.wisdplat.hmi.fragment.drivearea

import com.wisdplat.hmi.base.BaseFragment
import com.wisdplat.hmi.databinding.FragmentStartParkBinding
import com.wisdplat.hmi.viewmodel.ParkViewModel

class ParkStartFragment : BaseFragment<FragmentStartParkBinding, ParkViewModel>() {
    override fun FragmentStartParkBinding.initBinding() {

        //设置按钮位置为左边
        mBinding.parkSwitchButton.isChecked = false


    }
}