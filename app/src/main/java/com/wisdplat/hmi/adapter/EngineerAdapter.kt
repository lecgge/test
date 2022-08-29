package com.wisdplat.hmi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.wisdplat.hmi.R
import com.wisdplat.hmi.bean.EngineerSignals

/**
 *
@author tanqianpeng
@email  tqp0507@qq.com
@create 2022-8-1 12:30
 *
 */

class EngineerAdapter(val list: Array<Array<EngineerSignals>>) :
    RecyclerView.Adapter<EngineerAdapter.EngineerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EngineerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.engineer_item, parent, false)
        return EngineerViewHolder(view)
    }

    override fun onBindViewHolder(holder: EngineerViewHolder, position: Int) {
        //计算item的坐标，X的值和Y的值，即可在数组中获取到item实例
        val x = position / list.size
        val y = position % list.size
        try {
//            Log.d("TAG", "onBindViewHolder: $position")
            //注意传入的二维数组，与效果图是以左上到右下的连线为对称轴，因此数组中的位置与显示位置的对应关系==》 x,y -> y,x
            holder.bindView(list[y][x])
        } catch (e: Exception) {
            //数组越界
            e.printStackTrace()
//            Log.d("TAG", "onBindViewHolder: " + "X:" + x + "y:" + y)
        }
    }

    override fun getItemCount() = list.size * maxLen(list)

    //计算二维数组中,行的最大长度
    private fun maxLen(signals: Array<Array<EngineerSignals>>?): Int {
        var max = 0
        var temp = 0
        if (signals == null) {
            return 0
        }
        for (i in signals.indices) {
            temp = signals[i].size
            if (max < temp) {
                max = temp
            }
        }
        return max
    }

    class EngineerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.text_name)
        val content: TextView = itemView.findViewById(R.id.text_content)
        private val box: ImageView = itemView.findViewById(R.id.icon_box)
        private val car: ImageView = itemView.findViewById(R.id.icon_car)

        val layout: ConstraintLayout = itemView.findViewById(R.id.item_layout)

        fun bindView(signal: EngineerSignals) {
            if (signal != null) {
                name.text = signal.name
                content.text = signal.content
                car.setImageResource(signal.car)
                box.setImageResource(R.drawable.engineer_icon_box)
                layout.setBackgroundResource(R.drawable.park_four_circle_angle)
            }
        }

    }


}