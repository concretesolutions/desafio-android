package br.com.concrete.githubconcretechallenge.features.repositories.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.concrete.githubconcretechallenge.R
import br.com.concrete.githubconcretechallenge.features.repositories.viewmodel.RepositoriesListViewModel
import kotlinx.android.synthetic.main.activity_repositories_list.*
import org.koin.android.ext.android.inject

class RepositoriesListActivity : AppCompatActivity() {

    private val adapter: RepositoriesAdapter by inject()

    private val viewModel: RepositoriesListViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories_list)

        initializeView()

        testViewModel()
    }

    private fun initializeView() {
        recycler_repository_list.adapter = adapter
    }

    private fun testViewModel() {
        viewModel.printViewModel()
    }

}
