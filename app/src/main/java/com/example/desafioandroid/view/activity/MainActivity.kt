package com.example.desafioandroid.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders

import com.example.desafioandroid.R
import com.example.desafioandroid.view.fragment.RepositoryFragment
import com.example.desafioandroid.viewModel.activity.MainActivityViewModel

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG : String = javaClass.simpleName
    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (viewModel.backStackEntryCount == 0) {
            showView()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1)
            finish()
        super.onBackPressed()
    }

    private fun showView() {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        val repositoryFragment = RepositoryFragment()
        fragmentTransaction.replace(R.id.fragment_container, repositoryFragment)
            .addToBackStack(RepositoryFragment().TAG)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.backStackEntryCount = supportFragmentManager.backStackEntryCount
    }

}
