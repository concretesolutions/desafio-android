package br.com.guilherme.solution.ui.base

class Base {

    interface Presenter<in T> {
        fun subscribe()
        fun unsubscribe()
        fun attach(view: T)
    }

    interface View {
    }
}