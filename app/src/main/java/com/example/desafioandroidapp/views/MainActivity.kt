package com.example.desafioandroidapp.views

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroidapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels(
        factoryProducer = { MainActivityViewModelFactory() }
    )

    private val adapter = MainActivityAdapter(object: MainActivityAdapter.ItemsListener {
        override fun selectedItem(ownerName: String, repository: String) {
            val intent = Intent(applicationContext, RepositoryDetail::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                putExtra("title", repository)
                putExtra("ownerName", ownerName)
            }

            startActivity(intent)
        }
    })

    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lista.layoutManager = LinearLayoutManager(this)

        viewModel.getRepos(page)
        addOnScrollListener()
        setObservers()
        //setListeners()
    }

    private fun addOnScrollListener() {
            binding.lista.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                @SuppressLint("NotifyDataSetChanged")
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                    if (linearLayoutManager != null && isBottomOfList(linearLayoutManager) && viewModel.continueapi.value ?: true) {
                        ++page
                        viewModel.getRepos(page)
                    }
                }
            })
    }

    fun isBottomOfList(linearLayoutManager : LinearLayoutManager): Boolean {
        return linearLayoutManager.findLastCompletelyVisibleItemPosition() == adapter.items.size - 1
    }


    private fun setObservers(){
        this.viewModel.data.observe(this){ value ->
            if (value != null) {
                binding.lista.adapter = adapter
                adapter.setRepositories(value)
                adapter.notifyDataSetChanged()
            }
        }
    }

    fun setListeners(){
        binding.lista.setOnClickListener(){
            val intent = Intent(this, RepositoryDetail::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            intent.putExtra("title", viewModel.data.value?.get(1)?.name)
            startActivity(intent)
        }
    }


}