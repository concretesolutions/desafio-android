package com.germanofilho.core.ui

import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment(@LayoutRes private val layout: Int) : Fragment(layout){

    fun showSnackBar(view: View, msg: Int, buttonMsg: Int, onClickAction: (() -> Unit)){
        val snackBar = Snackbar.make(view, msg, Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction(buttonMsg) { onClickAction.invoke() }.show()
    }

    private var enableItem = true

    override fun onResume() {
        super.onResume()
        enableItem = true
    }

    override fun onPause() {
        super.onPause()
        enableItem = false
    }

    protected fun ignoreReplaceFragment(itemListener: (() -> Unit)) {
        if (enableItem) {
            itemListener.invoke()
        }
    }
}