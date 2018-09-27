package br.com.desafio.concrete.view.about

import android.os.Bundle
import android.view.MenuItem
import br.com.desafio.concrete.R
import br.com.desafio.concrete.base.BaseActivity
import br.com.desafio.concrete.extension.ActivityAnimation
import br.com.desafio.concrete.extension.finishActivity
import kotlinx.android.synthetic.main.about_activity.*
import kotlinx.android.synthetic.main.toolbar_include.*

/**
 * Created by Malkes on 26/09/2018.
 */
class AboutActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_activity)

        setupToolbar(toolbar, R.string.about_title, R.drawable.ic_close, true)


        webView.loadData(getString(R.string.about), "text/html; charset=UTF-8", null)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finishActivity(ActivityAnimation.TRANSLATE_DOWN)
        return true
    }
}