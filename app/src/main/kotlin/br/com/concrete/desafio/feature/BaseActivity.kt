package br.com.concrete.desafio.feature

import android.arch.lifecycle.LifecycleActivity
import br.com.concrete.desafio.extension.addStatusBarPadding
import kotlinx.android.synthetic.main.activity_repo_list.*

abstract class BaseActivity : LifecycleActivity() {

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        toolbar?.let {
            it.addStatusBarPadding()
            it.setNavigationOnClickListener { onBackPressed() }
        }
    }

}