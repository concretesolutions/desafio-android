package com.example.desafio_android.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.desafio_android.R
import com.example.desafio_android.model.RepositorieModel


class RepositoriesGitHubActivity: AppCompatActivity(), RepositoriesListFragment.OnRepositorieSelected{
    var mCurrentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fragment)
        mCurrentFragment = RepositoriesListFragment.newInstance()

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, mCurrentFragment as RepositoriesListFragment)
                .commit()
        }
    }

    override fun OnRepositorieSelected(repositorie: RepositorieModel) {
        val detailsFragment =
            RepositoriePullRequestFragment.newInstance(repositorie)
        ChangeFragment(detailsFragment)
    }

    private fun ChangeFragment(fragment: Fragment) {
        mCurrentFragment = fragment
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (mCurrentFragment is RepositoriePullRequestFragment) {
            val listFragment =
                RepositoriesListFragment.newInstance()
            ChangeFragment(listFragment)
        } else{
            finish()
        }
    }
}