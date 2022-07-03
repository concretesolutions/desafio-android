package com.example.desafioandroidapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafioandroidapp.R
import com.example.desafioandroidapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels(
        factoryProducer = { MainActivityViewModelFactory() }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lista.layoutManager = LinearLayoutManager(this)

        viewModel.getRepos()
        setObservers()
    }

    fun setObservers(){
        this.viewModel.data.observe(this){ value ->
            if (value != null) {
                val adapter = MainActivityAdapter()
                binding.lista.adapter = adapter
                adapter.setRepositories(value)
            }
        }
    }
}