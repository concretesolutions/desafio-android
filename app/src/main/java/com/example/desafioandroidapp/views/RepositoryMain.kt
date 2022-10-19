package com.example.desafioandroidapp.views

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroidapp.R
import com.example.desafioandroidapp.data.dto.Item
//import com.example.desafioandroidapp.R
import com.example.desafioandroidapp.databinding.RepositoryMainBinding

class RepositoryMain : AppCompatActivity() {

    private lateinit var binding : RepositoryMainBinding
    private val viewModel: RepositoryMainViewModel by viewModels(
        factoryProducer = { RepositoryMainViewModelFactory() }
    )

    private val adapter = RepositoryMainAdapter(object: RepositoryMainAdapter.ItemsListener {
        override fun selectedItem(item : Item) {
            val bundle = Bundle()
            bundle.putParcelable(R.string.ITEM.toString(),item)
            val intent = Intent(applicationContext, RepositoryDetail::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    })

    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RepositoryMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lista.layoutManager = LinearLayoutManager(this)

        viewModel.getRepositories(page)
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
                    if (linearLayoutManager != null && isBottomOfList(linearLayoutManager) && viewModel.continueapi.value != false) {
                        ++page
                        viewModel.getRepositories(page)
                    }
                }
            })
    }

    fun isBottomOfList(linearLayoutManager : LinearLayoutManager): Boolean {
        return linearLayoutManager.findLastCompletelyVisibleItemPosition() == adapter.items.size - 1
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun setObservers(){
        this.viewModel.data.observe(this){ value ->
            if (value != null) {
                binding.lista.adapter = adapter
                adapter.setRepositories(value)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun setListeners(){
        TODO()
    }


}