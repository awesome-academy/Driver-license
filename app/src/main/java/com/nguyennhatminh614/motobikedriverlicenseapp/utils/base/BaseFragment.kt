package com.nguyennhatminh614.motobikedriverlicenseapp.utils.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.dialog.LoadingDialog

abstract class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB,
) : Fragment() {

    private var _binding: VB? = null
    protected val viewBinding: VB
        get() = _binding as VB

    protected abstract val viewModel: BaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = bindingInflater(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        handleEvent()
        bindData()

        viewModel.hasException.observe(viewLifecycleOwner) {
            Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show()
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                LoadingDialog.showLoadingDialog(context)
            } else {
                LoadingDialog.hideLoadingDialog()
            }
        }
    }

    abstract fun initData()

    abstract fun handleEvent()

    abstract fun bindData()

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
