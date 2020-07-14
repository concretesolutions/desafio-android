package com.bassul.mel.app.ext

import android.content.Context
import android.provider.Settings.Global.getString
import androidx.appcompat.app.AlertDialog

class AlertDialogUtils {
    companion object {
        fun createAndShowAlertDialog(message: Int, context: Context) {
            val mAlertDialog = AlertDialog.Builder(context)
            mAlertDialog.setTitle("Erro")
            mAlertDialog.setMessage(context.getString(message))
            mAlertDialog.show()
        }
    }
}