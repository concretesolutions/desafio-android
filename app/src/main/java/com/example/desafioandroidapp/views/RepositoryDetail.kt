package com.example.desafioandroidapp.views

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafioandroidapp.R
import com.example.desafioandroidapp.data.dto.Pull
import com.example.desafioandroidapp.data.dto.RepositoryItem
import com.example.desafioandroidapp.databinding.RepositoryDetailBinding

class RepositoryDetail : AppCompatActivity() {

    private lateinit var binding : RepositoryDetailBinding
    private val viewModel: RepositoryDetailViewModel by viewModels(
        factoryProducer = { RepositoryDetailViewModelFactory() }
    )

    private val adapter = RepositoryDetailAdapter(object: RepositoryDetailAdapter.PullsListener{
        override fun selectedItem(pull : Pull){
            TODO("Not yet implemented")
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        val bundle = intent.extras
        val repositoryItem = bundle?.getParcelable<RepositoryItem>(R.string.ITEM.toString())
        super.onCreate(savedInstanceState)
        binding = RepositoryDetailBinding.inflate(layoutInflater)
        setContentView(R.layout.repository_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = repositoryItem?.name

        binding.pullsList.layoutManager = LinearLayoutManager(this)
        viewModel.getPulls(repositoryItem?.owner?.login ?: "", repositoryItem?.name ?: "")
        setObservers()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setObservers(){
        this.viewModel.data.observe(this){ value ->
            if(value != null){
                System.out.println("Tamaño: $value?.size")
                adapter.also { this.binding.pullsList.adapter = it }
                adapter.setPulls(value)
                adapter.notifyDataSetChanged()
            }
        }
    }
}