package br.com.repository.view.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toolbar
import br.com.repository.R
import br.com.repository.databinding.ActivityBaseActivityBinding

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())

        configureView()
        init()
        initToolbar()
        setupToolbar()
    }

    private fun setupToolbar() {
        if (supportActionBar != null) {
            supportActionBar?.title = getTitleToolbar()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun getBinding() = binding

    abstract fun configureView()
    abstract fun init()
    abstract fun getLayoutId(): Int
    abstract fun getTitleToolbar(): String
    abstract fun initToolbar()

}