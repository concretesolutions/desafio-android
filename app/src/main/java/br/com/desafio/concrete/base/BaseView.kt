package br.com.desafio.concrete.base

/**
 * Created by Malkes on 25/09/2018.
 */
interface BaseView {
    fun showLoadingIndicator(loadingVisible: Boolean)

    fun showUnexpectedError(throwable: Throwable?)
}