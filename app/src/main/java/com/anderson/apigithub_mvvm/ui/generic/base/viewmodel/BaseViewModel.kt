package br.com.anderson.apigithub_mvvm.ui.generic.base.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel

/**
 * Created by anderson on 21/09/19.
 */
open class BaseViewModel protected constructor() : ViewModel() {

    var showLoading = ObservableBoolean(false)
}