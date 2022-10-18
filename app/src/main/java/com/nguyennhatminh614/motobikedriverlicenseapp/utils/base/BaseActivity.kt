package com.nguyennhatminh614.motobikedriverlicenseapp.utils.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding>(
    private val bindingInflater: (LayoutInflater) -> T
) : AppCompatActivity() {

    private var _binding : T? = null

    protected val viewBinding: T
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater(layoutInflater)
        setContentView(viewBinding.root)

        initData()
        handleEvent()
        bindData()
    }

    abstract fun initData()

    abstract fun handleEvent()

    abstract fun bindData()

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
