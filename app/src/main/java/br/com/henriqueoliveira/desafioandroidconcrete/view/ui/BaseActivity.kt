package br.com.henriqueoliveira.desafioandroidconcrete.view.ui

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat

abstract class BaseActivity : AppCompatActivity(){

    fun setupToolbar(toolBar: Toolbar?, title: Int, icon: Int,
                     displayHome: Boolean = true) {

        toolBar?.let { toolbar ->
            setSupportActionBar(toolBar)
            supportActionBar?.let { actionBar ->
                if (title != -1) {
                    setToolbarTitle(toolbar, title)
                }

                actionBar.setDisplayShowHomeEnabled(displayHome)
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
        if(item?.itemId == android.R.id.home)
            finish()

        return super.onOptionsItemSelected(item)
    }
}