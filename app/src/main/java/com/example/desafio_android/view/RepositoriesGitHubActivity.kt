package com.example.desafio_android.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.desafio_android.R
import com.example.desafio_android.model.RepositorieModel


class RepositoriesGitHubActivity: AppCompatActivity(), RepositoriesListFragment.OnRepositorieSelected, RepositoriePullRequestFragment.OnBackClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fragment)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, RepositoriesListFragment.newInstance(), "repositoriesList")
                .commit()
        }
    }

    override fun OnRepositorieSelected(repositorie: RepositorieModel) {
        val detailsFragment =
            RepositoriePullRequestFragment.newInstance(repositorie)
        ChangeFragment(detailsFragment)
    }

    private fun ChangeFragment(detailsFragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, detailsFragment, "repositoriePullRequest")
            .addToBackStack(null)
            .commit()
    }

    override fun OnBackClicked() {
        val listFragment =
            RepositoriesListFragment.newInstance()
        ChangeFragment(listFragment)
    }

    override fun onBackPressed() {
        OnBackClicked()
    }
}