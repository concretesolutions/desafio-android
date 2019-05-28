package cl.jesualex.desafio_android.base.presentation

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast

/**
 * Created by jesualex on 18-02-19.
 */
interface ViewBase {
    fun getContext(): Context?

    fun showProgress(show: Boolean){}

    fun showMessage(message: String?) {
        message?.let { Toast.makeText(getContext(), it, Toast.LENGTH_LONG).show() }
    }

    fun showMessage(@StringRes message: Int) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show()
    }

    fun showErrorMessage(message: String?) {
        showMessage(message)
    }

    fun showErrorMessage(@StringRes message: Int) {
        showMessage(message)
    }
}
