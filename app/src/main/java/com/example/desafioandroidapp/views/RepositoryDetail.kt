package com.example.desafioandroidapp.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafioandroidapp.R
import com.example.desafioandroidapp.data.dto.RepositoryItem
import com.example.desafioandroidapp.databinding.RepositoryDetailBinding
import com.example.desafioandroidapp.databinding.RepositoryMainBinding

class RepositoryDetail : AppCompatActivity() {

    private lateinit var binding : RepositoryDetailBinding
    private val viewModel: RepositoryDetailViewModel by viewModels(
        factoryProducer = { RepositoryMainViewModelFactory() }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        val bundle = intent.extras
        val repositoryItem = bundle?.getParcelable<RepositoryItem>(R.string.ITEM.toString())
        super.onCreate(savedInstanceState)
        binding = RepositoryDetailBinding.inflate(layoutInflater)
        setContentView(R.layout.repository_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = repositoryItem?.name

        binding.pullsList.layoutManager = LinearLayoutManager(this)
        //viewModel.getPulls(viewModel.repositoryItem?.owner?.login ?: "", viewModel.repositoryItem?.name ?: "")

    }
}