package com.wisdplat.hmi.fragment.eng

import android.graphics.Rect
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wisdplat.hmi.R
import com.wisdplat.hmi.adapter.EngineerAdapter
import com.wisdplat.hmi.base.BaseFragment
import com.wisdplat.hmi.bean.EngineerSignals
import com.wisdplat.hmi.databinding.FragmentBotBinding
import com.wisdplat.hmi.util.ViewUtils
import com.wisdplat.hmi.viewmodel.BotViewModel

/**
 *
@author tanqianpeng
@email  tqp0507@qq.com
@create 2022-8-1 12:30
 *
 */
const val TAG = "BotFragment"

class BotFragment : BaseFragment<FragmentBotBinding, BotViewModel>() {
    val list = initList()

    override fun providerVMClass(): Class<BotViewModel> = BotViewModel::class.java

    override fun FragmentBotBinding.initBinding() {


        val adapter = EngineerAdapter(list)
        mBinding.sign.adapter = adapter
        mBinding.sign.setItemViewCacheSize(adapter.itemCount)
        mBinding.sign.layoutManager = GridLayoutManager(context, 6, RecyclerView.VERTICAL, false)

        val row = list.size
        mBinding.sign.addItemDecoration(BotDecoration(row))

        viewModel.changeSingal()
        viewModel.engineerSignals.observe(viewLifecycleOwner, Observer {
            val position = change(it)
            if (position == -1) {
            } else {
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun change(engineerSignals: EngineerSignals): Int {
        for (i in 0 until list.size) {
            if(list[i][0].name.equals(engineerSignals.name)){
                for (j in 0 until list[i].size) {
                    if (list[i][j].content.equals(engineerSignals.content)) {
                        list[i][j] = engineerSignals
                        return i * j
                    }
                }
            }
        }
        return -1
    }

}

class BotDecoration(private val row: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.getChildLayoutPosition(view) < row) {
            outRect.top = ViewUtils.dp2px(25f)
            outRect.left = ViewUtils.dp2px(15f)

        }
        if (parent.getChildLayoutPosition(view) >= row) {
            outRect.top = ViewUtils.dp2px(15f)
            outRect.left = ViewUtils.dp2px(15f)
        }


    }
}

fun initList(): Array<Array<EngineerSignals>> {
    val box = R.drawable.engineer_icon_box
    //第一个list 功能TJA
    val tja = arrayOf<EngineerSignals>(
        EngineerSignals("功能TJA", "TJA 01", R.drawable.engineer_icon_tja_car, 0),
        EngineerSignals("功能TJA", "TJA 02", R.drawable.engineer_icon_tja_car_yellow, 1),
        EngineerSignals("功能TJA", "TJA 03", R.drawable.engineer_icon_tja_car, 0)
    )
    val acc = arrayOf(
        EngineerSignals("功能ACC", "ACC 01", R.drawable.engineer_icon_acc_car, 0),
        EngineerSignals("功能ACC", "ACC 02", R.drawable.engineer_icon_acc_car, 0),
        EngineerSignals("功能ACC", "ACC 03", R.drawable.engineer_icon_acc_car_green, 1)
    )

    val fcw = arrayOf(
        EngineerSignals("功能FCW", "FCW 01", R.drawable.engineer_icon_fcw_car_yellow, 1),
        EngineerSignals("功能FCW", "FCW 02", R.drawable.engineer_icon_fcw_car, 0),
        EngineerSignals("功能FCW", "FCW 03", R.drawable.engineer_icon_fcw_car, 0)
    )
    val aeb = arrayOf(
        EngineerSignals("功能AEB", "AEB 01", R.drawable.engineer_icon_aeb_car_green, 1),
        EngineerSignals("功能AEB", "AEB 02", R.drawable.engineer_icon_aeb_car, 0)
    )

    val dms = arrayOf(
        EngineerSignals("功能DMS", "DMS 01", R.drawable.engineer_icon_dms_car, 0),
        EngineerSignals("功能DMS", "DMS 02", R.drawable.engineer_icon_dms_car, 0),
        EngineerSignals("功能DMS", "DMS 03", R.drawable.engineer_icon_dms_car_yellow, 1)
    )
    val rda = arrayOf(
        EngineerSignals("功能RDA", "RDA 01", R.drawable.engineer_icon_rda_car, 0),
        EngineerSignals("功能RDA", "RDA 02", R.drawable.engineer_icon_rda_car_yellow, 1),
        EngineerSignals("功能RDA", "RDA 03", R.drawable.engineer_icon_rda_car, 0),
        EngineerSignals("功能RDA", "RDA 04", R.drawable.engineer_icon_rda_car, 0),
        EngineerSignals("功能RDA", "RDA 05", R.drawable.engineer_icon_rda_car, 0),
        EngineerSignals("功能RDA", "RDA 06", R.drawable.engineer_icon_rda_car, 0)
    )

    return arrayOf(
        arrayOf(*tja),
        arrayOf(*acc),
        arrayOf(*fcw),
        arrayOf(*aeb),
        arrayOf(*dms),
        arrayOf(*rda)
    )
}
