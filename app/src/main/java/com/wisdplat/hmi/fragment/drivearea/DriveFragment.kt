package com.wisdplat.hmi.fragment.drivearea

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import com.wisdplat.hmi.R
import com.wisdplat.hmi.base.BaseFragment
import com.wisdplat.hmi.databinding.FragmentDriveBinding
import com.wisdplat.hmi.viewmodel.DriveViewModel

/**
 *
@author zoushunhua
@Email zouhs@foxmail.com
@create 2022-07-29 16:53
 *
 */
class DriveFragment : BaseFragment<FragmentDriveBinding, DriveViewModel>() {
    override fun FragmentDriveBinding.initBinding() {
        //设置关闭点击事件
        mBinding.labelOne.setOnClickListener {
            change(it)
        }
        //设置ACC点击事件
        mBinding.labelTwo.setOnClickListener {
            change(it)
        }
        //设置ALC点击事件
        mBinding.labelThree.setOnClickListener {
            change(it)
        }
        //设置关闭点击事件
        mBinding.labelFour.setOnClickListener {
            change(it)
        }
        //设置ACC点击事件
        mBinding.labelFive.setOnClickListener {
            change(it)
        }
        //设置ALC点击事件
        mBinding.labelSix.setOnClickListener {
            change(it)
        }
        //设置OFF点击事件
        mBinding.labelSeven.setOnClickListener {
            change(it)
        }
        //设置ACC点击事件
        mBinding.labelEight.setOnClickListener {
            change(it)
        }
        //设置TJA点击事件
        mBinding.labelNine.setOnClickListener {
            change(it)
        }
        //设置OFF点击事件
        mBinding.labelTen.setOnClickListener {
            change(it)
        }
        //设置ACC点击事件
        mBinding.labelEleven.setOnClickListener {
            change(it)
        }
        //设置TJA点击事件
        mBinding.labelTwelve.setOnClickListener {
            change(it)
        }
        //设置OFF点击事件
        mBinding.labelThirteen.setOnClickListener {
            change(it)
        }
        //设置ACC点击事件
        mBinding.labelFourteen.setOnClickListener {
            change(it)
        }
        //设置TJA点击事件
        mBinding.labelFifteen.setOnClickListener {
            change(it)
        }

    }

    @SuppressLint("ResourceAsColor")
    private fun change(view: View) {
        when (view) {
            mBinding.labelOne -> {
                mBinding.labelOne.background =
                    ContextCompat.getDrawable(
                        requireContext(),
                        (R.drawable.fun_sharp)
                    )
                mBinding.separatorOne.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.separatorTwo.setBackgroundColor(Color.parseColor("#BBBBBB"))
                mBinding.labelTwo.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.labelThree.setBackgroundColor(Color.parseColor("#606060"))
            }
            mBinding.labelTwo -> {
                mBinding.labelTwo.background =
                    ContextCompat.getDrawable(
                        requireContext(),
                        (R.drawable.fun_btn_blue)
                    )
                mBinding.separatorOne.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.separatorTwo.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.labelOne.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.labelThree.setBackgroundColor(Color.parseColor("#606060"))
            }
            mBinding.labelThree -> {
                mBinding.labelThree.background =
                    ContextCompat.getDrawable(
                        requireContext(),
                        (R.drawable.fun_btn_blue)
                    )
                mBinding.separatorTwo.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.separatorOne.setBackgroundColor(Color.parseColor("#BBBBBB"))
                mBinding.labelOne.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.labelTwo.setBackgroundColor(Color.parseColor("#606060"))
            }
            mBinding.labelFour -> {
                mBinding.labelFour.background =
                    ContextCompat.getDrawable(
                        requireContext(),
                        (R.drawable.fun_sharp)
                    )
                mBinding.separatorFour.setBackgroundColor(Color.parseColor("#BBBBBB"))
                mBinding.separatorThree.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.labelFive.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.labelSix.setBackgroundColor(Color.parseColor("#606060"))
            }
            mBinding.labelFive -> {
                mBinding.labelFive.background =
                    ContextCompat.getDrawable(
                        requireContext(),
                        (R.drawable.fun_btn_blue)
                    )
                mBinding.separatorFour.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.separatorThree.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.labelFour.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.labelSix.setBackgroundColor(Color.parseColor("#606060"))

            }
            mBinding.labelSix -> {
                mBinding.labelSix.background =
                    ContextCompat.getDrawable(
                        requireContext(),
                        (R.drawable.fun_btn_blue)
                    )
                mBinding.separatorFour.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.separatorThree.setBackgroundColor(Color.parseColor("#BBBBBB"))
                mBinding.labelFour.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.labelFive.setBackgroundColor(Color.parseColor("#606060"))
            }
            mBinding.labelSeven -> {
                mBinding.labelSeven.background =
                    ContextCompat.getDrawable(
                        requireContext(),
                        (R.drawable.fun_sharp)
                    )
                mBinding.separatorSix.setBackgroundColor(Color.parseColor("#BBBBBB"))
                mBinding.separatorFive.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.labelEight.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.labelNine.setBackgroundColor(Color.parseColor("#606060"))
            }
            mBinding.labelEight -> {
                mBinding.labelEight.background =
                    ContextCompat.getDrawable(
                        requireContext(),
                        (R.drawable.fun_btn_blue)
                    )
                mBinding.separatorSix.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.separatorFive.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.labelSeven.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.labelNine.setBackgroundColor(Color.parseColor("#606060"))
            }
            mBinding.labelNine -> {
                mBinding.labelNine.background =
                    ContextCompat.getDrawable(
                        requireContext(),
                        (R.drawable.fun_btn_blue)
                    )
                mBinding.separatorSix.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.separatorFive.setBackgroundColor(Color.parseColor("#BBBBBB"))
                mBinding.labelSeven.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.labelEight.setBackgroundColor(Color.parseColor("#606060"))
            }
            mBinding.labelTen -> {
                mBinding.labelTen.background =
                    ContextCompat.getDrawable(
                        requireContext(),
                        (R.drawable.fun_sharp)
                    )
                mBinding.separatorEight.setBackgroundColor(Color.parseColor("#BBBBBB"))
                mBinding.separatorSeven.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.labelEleven.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.labelTwelve.setBackgroundColor(Color.parseColor("#606060"))
            }
            mBinding.labelEleven -> {
                mBinding.labelEleven.background =
                    ContextCompat.getDrawable(
                        requireContext(),
                        (R.drawable.fun_btn_blue)
                    )
                mBinding.separatorEight.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.separatorSeven.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.labelTen.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.labelTwelve.setBackgroundColor(Color.parseColor("#606060"))
            }
            mBinding.labelTwelve -> {
                mBinding.labelTwelve.background =
                    ContextCompat.getDrawable(
                        requireContext(),
                        (R.drawable.fun_btn_blue)
                    )
                mBinding.separatorEight.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.separatorSeven.setBackgroundColor(Color.parseColor("#BBBBBB"))
                mBinding.labelTen.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.labelEleven.setBackgroundColor(Color.parseColor("#606060"))
            }
            mBinding.labelThirteen -> {
                mBinding.labelThirteen.background =
                    ContextCompat.getDrawable(
                        requireContext(),
                        (R.drawable.fun_sharp)
                    )
                mBinding.separatorTen.setBackgroundColor(Color.parseColor("#BBBBBB"))
                mBinding.separatorNine.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.labelFourteen.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.labelFifteen.setBackgroundColor(Color.parseColor("#606060"))
            }
            mBinding.labelFourteen -> {
                mBinding.labelFourteen.background =
                    ContextCompat.getDrawable(
                        requireContext(),
                        (R.drawable.fun_btn_blue)
                    )
                mBinding.separatorTen.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.separatorNine.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.labelThirteen.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.labelFifteen.setBackgroundColor(Color.parseColor("#606060"))
            }
            mBinding.labelFifteen -> {
                mBinding.labelFifteen.background =
                    ContextCompat.getDrawable(
                        requireContext(),
                        (R.drawable.fun_btn_blue)
                    )
                mBinding.separatorTen.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.separatorNine.setBackgroundColor(Color.parseColor("#BBBBBB"))
                mBinding.labelThirteen.setBackgroundColor(Color.parseColor("#606060"))
                mBinding.labelFourteen.setBackgroundColor(Color.parseColor("#606060"))
            }
        }
    }
}


