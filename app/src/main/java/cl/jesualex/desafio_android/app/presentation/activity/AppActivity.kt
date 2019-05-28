package cl.jesualex.desafio_android.app.presentation.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cl.jesualex.desafio_android.R
import cl.jesualex.desafio_android.repo.presentation.fragment.RepoFragment

/**
 * Created by jesualex on 2019-05-28.
 */

class AppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, RepoFragment())
            .commit()

        setContentView(R.layout.activity_app)
    }
}