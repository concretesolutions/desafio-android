package com.example.desafioandroid.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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

        viewModel.loadPullOwner(args.owner,args.repoName)

        binding.rvPull.layoutManager = LinearLayoutManager(context)
        val adapter = PullAdapter()
        binding.rvPull.adapter = adapter

        adapter.setOnItemClickListener {
            Log.i("Test",it.toString())
            Toast.makeText(context, "Abrir Navegador", Toast.LENGTH_SHORT).show()
        }

        viewModel.pullModel.observe(viewLifecycleOwner) { pullList ->
            adapter.submitList(pullList)
            adapter.notifyDataSetChanged()
        }

        viewModel.isLoading.observe(viewLifecycleOwner){it
            binding.bprogressPull.isVisible = it
        }
        return binding.root
    }
}
