package com.wisdplat.hmi.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.wisdplat.hmi.util.LogUtil
import java.lang.reflect.ParameterizedType

/**
 *
@author zoushunhua
@Email zouhs@foxmail.com
@create 2022-08-1 17:15
 *
 */

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : Fragment(),
    BaseBinding<VB> {

    lateinit var mBinding: VB
    lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LogUtil.d("onCreateView")
        mBinding = getViewBinding(inflater, container)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LogUtil.d("onViewCreated")
        initVM()
        mBinding.initBinding()
        mBinding.lifecycleOwner = viewLifecycleOwner
        initData()
    }

    private fun initVM() {
        providerVMClass()?.let {
            viewModel = ViewModelProvider(this)[it]
            lifecycle.addObserver(viewModel)
        }

    }


    override fun onDestroy() {
//        lifecycle.removeObserver(viewModel)
        super.onDestroy()
        //此处记得取消绑定，避免内存泄露
        if (::mBinding.isInitialized) {
            mBinding.unbind()
        }
    }

    open fun providerVMClass(): Class<VM>? = null

    private fun initData() {
    }

    private fun <VB : ViewBinding> Any.getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): VB {
        LogUtil.d("getViewBinding")
        val vbClass =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<VB>>()
        val inflate = vbClass[0].getDeclaredMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        return inflate.invoke(null, inflater, container, false) as VB
    }
}

