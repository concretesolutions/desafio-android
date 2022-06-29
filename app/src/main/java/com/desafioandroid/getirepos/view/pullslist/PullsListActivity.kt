package com.desafioandroid.getirepos.view.pullslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.desafioandroid.getirepos.databinding.ActivityPullsListBinding

class PullsListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPullsListBinding
    private lateinit var owner: String
    private lateinit var repository: String
    private val viewModel: PullsViewModel by viewModels(
        factoryProducer = { PullsViewModelFactory() }
    )

    private val pullsListAdapter = PullsListAdapter(object: PullsListAdapter.PullsListActivityListener {
        override fun pullSelected(pullLink: String) {
            onPullClickCall(pullLink)
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPullsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        owner = intent.extras?.getString("owner").toString()
        repository = intent.extras?.getString("repository").toString()
        this.setTitle(repository)

        binding.pullsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.pullsRecyclerView.adapter = pullsListAdapter
        fillPullsInList()
    }

    fun fillPullsInList() {
        binding.pullsProgressBar.isVisible = true
        viewModel.getPulls(owner, repository)
        viewModel.pulls.observe(this) {
            value ->
            if(null != value) {
                pullsListAdapter.setPullsItems(value)
                binding.pullsProgressBar.isVisible = false
            }
        }
    }

    fun onPullClickCall(pullLink: String) {
        Toast.makeText(this, "List item clicked!!!!", Toast.LENGTH_LONG).show()
    }
}