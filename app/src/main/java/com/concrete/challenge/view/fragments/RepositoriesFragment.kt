package com.concrete.challenge.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.concrete.challenge.R
import com.concrete.challenge.adapter.RepositoryAdapter
import com.concrete.challenge.data.RepositoryEntity
import com.concrete.challenge.databinding.FragmentRepositoriesBinding
import com.concrete.challenge.presentation.viewmodel.RepositoryViewModel

class RepositoriesFragment : Fragment() {

    //private val adapter by lazy { RepositoryAdapter() }
    private val layoutManager by lazy { LinearLayoutManager(activity) }
    private val viewModel by lazy { ViewModelProvider(this).get(RepositoryViewModel::class.java) }

    private var _binding: FragmentRepositoriesBinding? = null
    private val binding get() = _binding!!

    private val repositoriesList = listOf(
        RepositoryEntity("panchyh97","Francisca Hernández","desafioandroid",
            "Lorem ipsum dolor sit", 1, 1),
        RepositoryEntity("johndoe","John Doe","desafioandroid",
            "Lorem ipsum dolor sit", 1, 1),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        loadList()
    }

    private fun initRecyclerView() {
        binding.rvRepository.layoutManager = layoutManager
        val adapter = RepositoryAdapter(repositoriesList)
        binding.rvRepository.adapter = adapter
    }

    private fun loadList() {
        viewModel.repositoriesList.observe(viewLifecycleOwner, ::add)
    }
    
    private fun add(repositoriesList: List<String>) {
        val list = listOf<String>("1", "2")
        //adapter.addItems(list)
    }

}