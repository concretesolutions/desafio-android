package com.concrete.challenge.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.concrete.challenge.R
import com.concrete.challenge.ui.adapters.RepositoryAdapter
import com.concrete.challenge.data.RepositoryEntity
import com.concrete.challenge.databinding.FragmentRepositoriesBinding
import com.concrete.challenge.presentation.viewmodel.RepositoryViewModel
import com.concrete.challenge.utils.Constants.TAG

class RepositoriesFragment : Fragment() {

    //private val adapter by lazy { RepositoryAdapter() }
    private val layoutManager by lazy { LinearLayoutManager(activity) }
    private val viewModel by lazy { ViewModelProvider(this).get(RepositoryViewModel::class.java) }

    private lateinit var binding: FragmentRepositoriesBinding

    private val repositoriesList = listOf(
        RepositoryEntity("panchyh97","Francisca Hernández","desafioandroid",
            "Lorem ipsum dolor sit", 1, 1),
        RepositoryEntity("johndoe","John Doe","desafioandroid",
            "Lorem ipsum dolor sit", 1, 1),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepositoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        loadList()
    }

    private fun initRecyclerView() {
        binding.rvRepository.layoutManager = layoutManager
        val adapter = RepositoryAdapter(repositoriesList, RepositoryManager())
        binding.rvRepository.adapter = adapter
    }

    private fun loadList() {
        viewModel.repositoriesList.observe(viewLifecycleOwner, ::add)
    }

    private fun add(repositoriesList: List<String>) {
        val list = listOf("1", "2")
        Log.i(TAG, list.toString() + repositoriesList.toString())
        //adapter.addItems(list)
    }

    inner class RepositoryManager : RepositoryAdapter.AdapterManager {
        override fun onRepositoryClicked(repositoryClicked: RepositoryEntity) {
            findNavController().navigate(
                R.id.action_repositoriesFragment_to_pullRequestFragment
            )
        }
    }
}