package com.nguyennhatminh614.motobikedriverlicenseapp.utils.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import com.nguyennhatminh614.motobikedriverlicenseapp.databinding.DialogLoadingLayoutBinding

object LoadingDialog {
    private var dialog: AlertDialog? = null

    fun showLoadingDialog(context: Context?) {
        val windowLayoutParams = WindowManager.LayoutParams()
        windowLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        windowLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT

        if(dialog == null) {
            dialog = AlertDialog.Builder(context)
                .setView(DialogLoadingLayoutBinding.inflate(LayoutInflater.from(context)).root)
                .setCancelable(false)
                .create()
        }

        dialog?.show()
        dialog?.window?.attributes = windowLayoutParams
    }

    fun hideLoadingDialog() {
        dialog?.dismiss()
    }
}
