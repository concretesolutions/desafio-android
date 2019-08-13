package com.rafael.fernandes.desafioconcrete.ui.base


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.rafael.fernandes.desafioconcrete.presentation.resources.Resource
import com.rafael.fernandes.desafioconcrete.presentation.resources.ResourceState
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    protected lateinit var mViewModel: V

    private lateinit var mViewDataBinding: T

    private val viewModel: Class<V>
        get() {
            val type = (javaClass
                .genericSuperclass as ParameterizedType).actualTypeArguments[1]
            return type as Class<V>
        }

    protected abstract fun layoutId(): Int

    open fun beforeSetContentView() {}

    protected abstract fun myOnCreate(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        performDataBinding()
        myOnCreate(savedInstanceState)
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this@BaseActivity, layoutId())

        this.mViewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModel)

        mViewModel.onViewCreated()
        mViewDataBinding.executePendingBindings()
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    protected open fun <H> onStateChange(resource: Resource<H>) {
        when (resource.state) {
            ResourceState.LOADING -> onLoading()
            ResourceState.ERROR -> onError(resource.message)
            ResourceState.SUCCESS -> onSuccess(resource.data)
        }
    }

    fun <T> gotoNextScreen(aClass: Class<T>, bundle: Bundle? = null, finish: Boolean = true) {
        var intent = Intent(this, aClass)
        bundle?.let {
            intent.putExtras(bundle)
        }

        startActivity(intent)

        if (finish) {
            finish()
        }
    }

    fun <T> gotoNextScreen(aClass: Class<T>, bundle: Bundle?, requestCode: Int) {
        var intent = Intent(this, aClass)
        bundle?.let {
            intent.putExtras(bundle)
        }

        startActivityForResult(intent, requestCode)
    }

    open fun onLoading() {

    }

    open fun onError(message: String? = null) {

    }

    open fun <H> onSuccess(data: H?) {

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    protected fun addBackButtonOnToolbar() {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }
    }

    protected fun finishWithResultOk() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}
