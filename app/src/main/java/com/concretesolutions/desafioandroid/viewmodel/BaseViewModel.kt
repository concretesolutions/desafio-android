package com.concretesolutions.desafioandroid.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.content.Context

open class BaseViewModel {

    protected val context: Context
    protected var status: MutableLiveData<BaseViewModel.LoadStatus> = MutableLiveData()

    constructor(ctx: Context) {
        context = ctx
        status.value = LoadStatus(false, "")
    }

    fun getLoadStatus(): MutableLiveData<LoadStatus> {
        return status
    }

    class LoadStatus(val finished: Boolean, val message: String)
}