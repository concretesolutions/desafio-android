package com.desafioandroid.getirepos.view.pullslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.desafioandroid.getirepos.databinding.ActivityPullsListBinding

class PullsListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPullsListBinding
    private val viewModel: PullsViewModel by viewModels(
        factoryProducer = { PullsViewModelFactory() }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPullsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}