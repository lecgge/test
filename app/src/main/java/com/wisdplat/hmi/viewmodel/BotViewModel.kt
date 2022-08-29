package com.wisdplat.hmi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wisdplat.hmi.base.BaseViewModel
import com.wisdplat.hmi.bean.EngineerSignals
import com.wisdplat.hmi.repository.EngineerRepository
import kotlinx.coroutines.launch

/**
@author tanqianpeng
@email  tqp0507@qq.com
@create 2022-8-1 12:30
 */
class BotViewModel : BaseViewModel() {

    private var _engineerSignals = MutableLiveData<EngineerSignals>()
    val engineerSignals get() = _engineerSignals

    fun changeSingal(){
        viewModelScope.launch {
            EngineerRepository.signals().collect {
                _engineerSignals.value = it
            }
        }
    }
}