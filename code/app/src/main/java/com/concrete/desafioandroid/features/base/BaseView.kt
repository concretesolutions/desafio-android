package com.concrete.desafioandroid.features.base


interface BaseView {
    fun showError(messsage: String?)
    fun showProgress(show: Boolean)
}