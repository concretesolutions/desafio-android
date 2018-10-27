package br.com.andreyneto.desafioandroid.ui.base

interface BaseView<T> {
    fun setPresenter(presenter: T)
}