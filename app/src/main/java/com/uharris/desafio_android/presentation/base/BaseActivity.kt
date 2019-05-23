package com.uharris.desafio_android.presentation.base

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

open class BaseActivity: AppCompatActivity() {

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun getActivity(): Activity {
        return this
    }

    fun showLoader() {
        loaderDialog = LoadingDialog.newInstance()

        loaderDialogList.add(loaderDialog!!)

        val ft = this.supportFragmentManager.beginTransaction()
        ft.add(loaderDialog!!, "InformationAlertDialogFragment")
        ft.commitAllowingStateLoss()
    }

    fun hideLoader() {
        for (ldf in loaderDialogList) {
            ldf.dismissAllowingStateLoss()
        }
    }

    fun showMessage(message: String) {
        hideKeyboard()
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        private var loaderDialog: LoadingDialog? = null
        private val loaderDialogList = ArrayList<LoadingDialog>()
    }
}