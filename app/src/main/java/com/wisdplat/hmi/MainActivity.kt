package com.wisdplat.hmi

import androidx.fragment.app.Fragment
import com.wisdplat.hmi.base.BaseActivity
import com.wisdplat.hmi.databinding.ActivityMainBinding
import com.wisdplat.hmi.fragment.cararea.CarAreaFragment
import com.wisdplat.hmi.fragment.drivearea.DriveAreaFragment
import com.wisdplat.hmi.fragment.eng.EngineerFragment
import com.wisdplat.hmi.fragment.instruction.InstructionFragment
import com.wisdplat.hmi.fragment.lowriding.LowridingFragment
import com.wisdplat.hmi.fragment.meter.MeterFragment
import com.wisdplat.hmi.util.LogUtil


/**
 * @author songyinan
 * @email 312966673@qq.com
 * @creat 2022-07-26
 * */

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val TAG = "MainActivity"
    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment, fragment)
        transaction.commit()
    }

    override fun ActivityMainBinding.initBinding() {
        navigationOperate()
    }

    private fun navigationOperate() {
        mBinding.navigationView.setCheckedItem(R.id.engineering_mode)
        mBinding.navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.engineering_mode -> {
                    LogUtil.d(TAG, "engineering_mode")
                    item.isCheckable = true
                    replaceFragment(EngineerFragment())
                    true
                }

                R.id.function_control -> {
                    LogUtil.d(TAG, "function_control")
                    item.isCheckable = true
                    replaceFragment(DriveAreaFragment())
                    true
                }

                R.id.parking -> {
                    LogUtil.d(TAG, "parking")
                    item.isCheckable = true
                    replaceFragment(CarAreaFragment())
                    true
                }

                R.id.meter -> {
                    LogUtil.d(TAG, "meter")
                    item.isCheckable = true
                    replaceFragment(MeterFragment())
                    true
                }

                R.id.lowriding -> {
                    LogUtil.d(TAG, "lowriding")
                    item.isCheckable = true
                    replaceFragment(LowridingFragment())
                    true
                }


                R.id.instruction -> {
                    LogUtil.d(TAG, "instruction")
                    item.isCheckable = true
                    replaceFragment(InstructionFragment())
                    true
                }
                else -> false
            }
        }
    }
}

