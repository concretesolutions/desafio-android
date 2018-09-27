package br.com.concrete.desafio.base

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import br.com.concrete.desafio.data.extension.observe
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

abstract class BaseActivity : AppCompatActivity() {

    open fun handleError(error: Throwable) {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
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