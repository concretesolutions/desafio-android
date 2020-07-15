package com.bassul.mel.app.ext

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.bassul.mel.app.R

class AlertDialogUtils {
    companion object {
        fun createAndShowAlertDialog(message: Int, context: Context) {
            val mAlertDialog = AlertDialog.Builder(context)
            mAlertDialog.setTitle(context.getString(R.string.ErroTitleAlertDialog))
            mAlertDialog.setMessage(context.getString(message))
            mAlertDialog.show()
        }
    }
}