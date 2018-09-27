package br.com.desafio.concrete.base

/**
 * Created by Malkes on 25/09/2018.
 */
interface BasePresenter<in V : BaseView> {
    fun attachView(view: V)
    fun detachView()
}