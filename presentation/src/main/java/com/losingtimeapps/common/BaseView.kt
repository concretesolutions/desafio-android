package com.losingtimeapps.common

import android.content.Context
import com.losingtimeapps.domain.Error

interface BaseView {

    fun getContext(): Context
    fun showProgress(state: Boolean)

    fun showSnackbarError(error: Error)

    fun notDataLoaded(state:Boolean)

}