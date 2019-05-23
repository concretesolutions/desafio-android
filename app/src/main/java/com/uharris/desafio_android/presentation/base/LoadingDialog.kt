package com.uharris.desafio_android.presentation.base

import android.app.Dialog
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.uharris.desafio_android.R

class LoadingDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.loading_dialog, null)
        view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        val color = 0

        view.background = ColorDrawable(color)

        val dialog = Dialog(activity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        dialog.setContentView(view)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(color))
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        val progressBar = view.findViewById(R.id.fragment_loading_dialog_progress_bar) as ProgressBar

        progressBar.indeterminateDrawable.setColorFilter(ContextCompat.getColor(context!!, R.color.colorAccent), PorterDuff.Mode.MULTIPLY)

        return dialog
    }

    override fun onStart() {
        super.onStart()

        if (dialog == null)
            return

        val scale = activity!!.applicationContext.resources.displayMetrics.density
        var dialogWidth = (100 * scale + 0.5f).toInt()
        var dialogHeight = (100 * scale + 0.5f).toInt()

        if (dialogWidth == 0)
            dialogWidth = LinearLayout.LayoutParams.WRAP_CONTENT

        if (dialogHeight == 0)
            dialogHeight = LinearLayout.LayoutParams.WRAP_CONTENT

        dialog.window!!.setLayout(dialogWidth, dialogHeight)
    }

    companion object {

        fun newInstance(): LoadingDialog {
            val loadingDialog = LoadingDialog()
            loadingDialog.isCancelable = false
            return loadingDialog
        }
    }
}