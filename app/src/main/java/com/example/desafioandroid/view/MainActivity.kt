package com.example.desafioandroid.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.example.desafioandroid.R

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), Observer {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        showView()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun update(p0: Observable?, p1: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun showView() {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        val repositoryFragment = RepositoryFragment()
        fragmentTransaction.replace(R.id.fragment_container, repositoryFragment)
            .addToBackStack(RepositoryFragment().TAG)
            .commit()
    }

}
