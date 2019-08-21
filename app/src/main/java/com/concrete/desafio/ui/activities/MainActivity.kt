package com.concrete.desafio.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        var layMan = LinearLayoutManager(this)

        var previousTotal = 0
        var loading = true
        var visibleThreshold = 5
        var firstVisibleItem: Int
        var visibleItemCount: Int
        var totalItemCount: Int
        var page = 1

        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)


                visibleItemCount = recycler_view.getChildCount()
                totalItemCount = layMan.getItemCount()
                firstVisibleItem = layMan.findFirstVisibleItemPosition()

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false
                        previousTotal = totalItemCount
                    }
                }
                if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
                    loading = true
                    progress_bottom.visibility = View.VISIBLE
                    repositorioViewModel.buscarRepositorios(page++)
                }
            }
        })

        setSupportActionBar(toolbar)
        toolbar.setTitle("Github Repositórios")
        recycler_view.layoutManager = layMan
        recycler_view.adapter = repositoriosAdapter

        repositorioViewModel.getRepositorios()?.observe(this, Observer{ data ->
            data?.let{
                loading = false
                progress_bottom.visibility = View.GONE
                if(it.isEmpty()){
                    Toast.makeText(this, "Lista vazia!", Toast.LENGTH_LONG).show()
                } else {
                    progress_center.visibility = View.GONE
                    repositoriosAdapter.add(it)
                }
            }
        })
    }
}
