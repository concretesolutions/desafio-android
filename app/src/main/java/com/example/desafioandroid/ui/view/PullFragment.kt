package com.example.desafioandroid.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafioandroid.databinding.FragmentPullListBinding
import com.example.desafioandroid.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PullFragment : Fragment() {

    private lateinit var binding: FragmentPullListBinding
    private val args: PullFragmentArgs by navArgs()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPullListBinding.inflate(inflater, container, false)

        binding.rvPull.layoutManager = LinearLayoutManager(context)

        viewModel.onCreatePullOwner(args.owner,args.repoName)

        viewModel.pullModel.observe(viewLifecycleOwner) {
            Log.i("pullModel",it.toString())
            if (it != null) {
                val adapter =
                    PullRecyclerViewAdapter(it,
                        object : PullRecyclerViewAdapter.PullSelectionListener {
                            override fun select(pullName: String) {
                                Log.i("PullFragment", pullName)
                            }
                        })
                adapter.notifyDataSetChanged()
                binding.rvPull.adapter = adapter
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner){it
            binding.bprogressPull.isVisible = it
        }
        return binding.root
    }
}
