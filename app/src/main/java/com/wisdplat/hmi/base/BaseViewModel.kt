package com.wisdplat.hmi.base

import androidx.lifecycle.*
import com.wisdplat.hmi.util.LogUtil

/**
 *
@author zoushunhua
@Email zouhs@foxmail.com
@create 2022-08-2 9:18
 *
 */

abstract class BaseViewModel : ViewModel(), ViewModelLifecycle {
    private lateinit var lifecycleOwner: LifecycleOwner

    override fun onAny(owner: LifecycleOwner, event: Lifecycle.Event) {
        this.lifecycleOwner = owner
    }

    override fun onCreate() {
        LogUtil.d("TAG7", "onCreate: ")
    }

    override fun onStart() {
        LogUtil.d("TAG7", "onStart: ")
    }

    override fun onResume() {
        LogUtil.d("TAG77", "onResume: ")
    }

    override fun onPause() {
        LogUtil.d("TAG", "onPause: ")
    }

    override fun onStop() {
        LogUtil.d("TAG7", "onStop: ")
    }

    override fun onDestroy() {
        LogUtil.d("TAG7", "onDestroy: ")
    }
}

interface ViewModelLifecycle : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onAny(owner: LifecycleOwner, event: Lifecycle.Event)

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume()

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause()

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop()

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy()
}

