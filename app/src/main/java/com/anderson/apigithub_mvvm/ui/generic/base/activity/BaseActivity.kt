package br.com.anderson.apigithub_mvvm.ui.generic.base.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import br.com.anderson.apigithub_mvvm.ui.generic.base.viewmodel.BaseViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Created by anderson on 21/09/19.
 */
abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    protected lateinit var viewModel: V
    protected lateinit var bind: T

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun getViewModelClass(): Class<V>

    protected abstract fun init()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        bind = DataBindingUtil.setContentView(this, getLayoutId())

        viewModel = ViewModelProviders.of(this, this.viewModelProvider).get(getViewModelClass())

        init()
    }
}
