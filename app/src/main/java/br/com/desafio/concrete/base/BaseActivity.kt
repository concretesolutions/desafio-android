package br.com.desafio.concrete.base

import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import br.com.desafio.concrete.extension.finishActivity

/**
 * Created by Malkes on 24/09/2018.
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {
    override fun showLoadingIndicator(loadingVisible: Boolean) {

    }

    override fun showUnexpectedError(throwable: Throwable?) {
        throwable?.printStackTrace()
    }


    fun setupToolbar(toolBar: Toolbar?, title: Int, icon: Int,
                     displayHome: Boolean = true) {

        toolBar?.let { toolbar ->
            setSupportActionBar(toolBar)
            supportActionBar?.let { actionBar ->
                    if (title != -1) {
                        setToolbarTitle(toolbar, title)
                    }

                    actionBar.setDisplayShowHomeEnabled(displayHome)
                    actionBar.setDisplayHomeAsUpEnabled(displayHome)
                    if (icon != -1 && displayHome) {
                        toolbar.navigationIcon = ContextCompat.getDrawable(this, icon)
                    }
            }
        }
    }

    private fun setToolbarTitle(toolbar: Toolbar, title: Int) {
        toolbar.title = getString(title)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home) finishActivity()

        return super.onOptionsItemSelected(item)
    }
}