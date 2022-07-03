package com.desafioandroid.getirepos.view.pullslist

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
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
        binding.pullsRecyclerView.addItemDecoration(DividerItemDecoration(this, OrientationHelper.VERTICAL))
        binding.isEmptyPullsListText.isVisible = false
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
                if (pullsListAdapter.itemCount == 0) {
                    binding.isEmptyPullsListText.isVisible = true
                }
            }
        }
    }

    fun onPullClickCall(pullLink: String) {
        val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(pullLink))
        startActivity(intent)
    }
}