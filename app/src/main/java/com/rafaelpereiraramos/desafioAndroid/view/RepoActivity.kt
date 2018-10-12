package com.rafaelpereiraramos.desafioAndroid.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rafaelpereiraramos.desafioAndroid.R
import kotlinx.android.synthetic.main.activity_repo.*

/**
 * Created by Rafael P. Ramos on 12/10/2018.
 */
class RepoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)

        setUI()
    }

    private fun setUI() {
        toolbar.setTitle(R.string.app_name)

        setSupportActionBar(toolbar)
    }
}
