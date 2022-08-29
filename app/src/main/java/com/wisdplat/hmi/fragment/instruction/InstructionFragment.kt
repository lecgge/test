package com.wisdplat.hmi.fragment.instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wisdplat.hmi.R

/**
 * @author songyinan
 * @email songynes@foxmail.com
 * @creat 2022-07-26
 * */
class InstructionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_instruction, container, false)
    }

}
