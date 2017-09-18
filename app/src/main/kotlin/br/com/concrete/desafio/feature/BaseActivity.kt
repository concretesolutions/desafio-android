package br.com.concrete.desafio.feature

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.Toolbar
import br.com.concrete.desafio.R
import br.com.concrete.desafio.extension.addStatusBarMargin
import br.com.concrete.sdk.data.remote.exception.NotFoundException
import br.com.concrete.sdk.extension.observe
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

abstract class BaseActivity : LifecycleActivity() {

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        findViewById<Toolbar>(R.id.toolbar)?.apply {
            addStatusBarMargin()
            setNavigationOnClickListener { onBackPressed() }
        }
    }

    open fun handleError(error: Throwable) {

    }
}

class Delegate<T : BaseViewModel>(val clazz: KClass<T>) {

    private var viewModel: T? = null

    operator fun getValue(thisRef: BaseActivity, property: KProperty<*>): T {
        if (viewModel == null) {
            viewModel = ViewModelProviders.of(thisRef).get(clazz.java)
            viewModel!!.errorLiveData.observe(thisRef, thisRef::handleError)
        }
        return viewModel!!
    }

}

fun <T : BaseViewModel> viewModelProvider(clazz: KClass<T>) = Delegate(clazz)