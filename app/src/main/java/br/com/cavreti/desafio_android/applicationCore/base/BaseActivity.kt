package br.com.cavreti.desafio_android.applicationCore.base
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

open class BaseActivity: AppCompatActivity(), BaseContract.BaseView {

    fun setupToolbar(toolbar: Toolbar?, title: String?) {
        setupToolbar(title, null, toolbar)
    }

    fun setupToolbar(title: String?, subTitle: String?, toolbar: Toolbar?) {
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

            if (title != null) {
                supportActionBar!!.title = title
            }

            if (subTitle != null) {
                supportActionBar!!.subtitle = subTitle
            }
        }
    }
}