package com.concrete.desafio.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.concrete.desafio.R
import com.concrete.desafio.ui.RepositoriosAdapter
import com.concrete.desafio.viewmodels.RepositorioViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val repositorioViewModel: RepositorioViewModel by viewModel()

    val repositoriosAdapter: RepositoriosAdapter by lazy {
        RepositoriosAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        recycler_view.adapter = repositoriosAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)

        repositorioViewModel.getRepositorios()?.observe(this, Observer{ data ->
            data?.let{

                if(it.isEmpty()){
                    Toast.makeText(this, "Lista vazia!", Toast.LENGTH_LONG).show()
                } else {
                    repositoriosAdapter.add(it)
                }
            }
        })
    }
}
