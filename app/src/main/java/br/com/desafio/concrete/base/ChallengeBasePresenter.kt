package br.com.desafio.concrete.base

/**
 * Created by Malkes on 25/09/2018.
 */
open class ChallengeBasePresenter<V : BaseView> : BasePresenter<V>{

    var view: V? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}