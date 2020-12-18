package com.ccortez.desafioconcreteapplication.view.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ccortez.desafioconcreteapplication.R
import com.ccortez.desafioconcreteapplication.databinding.FragmentRepositoryListBinding
import com.ccortez.desafioconcreteapplication.di.Injectable
import com.ccortez.desafioconcreteapplication.service.model.Items
import com.ccortez.desafioconcreteapplication.service.model.Repositories
import com.ccortez.desafioconcreteapplication.view.adapter.RepositoryAdapter
import com.ccortez.desafioconcreteapplication.view.callback.RepositoryClickCallback
import com.ccortez.desafioconcreteapplication.viewmodel.RepositoryListViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class RepositoryListFragment : Fragment(), Injectable {

    private lateinit var repositoryAdapter: RepositoryAdapter
    private lateinit var binding: FragmentRepositoryListBinding
    var mContext: Context? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repository_list, container, false)
        repositoryAdapter = RepositoryAdapter(repositoryClickCallback)
        binding!!.carList.adapter = repositoryAdapter
        binding!!.setIsLoading(true)
        mContext = requireActivity().getApplicationContext()
        return binding!!.getRoot()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        ).get(RepositoryListViewModel::class.java)
        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: RepositoryListViewModel) { // Update the list when the data changes
        viewModel.repositoriesObservable
            .observe(viewLifecycleOwner, Observer { repositories ->
                if (repositories != null) {
                    binding!!.isLoading = false
                    repositoryAdapter!!.setRepositories(repositories)
                }
            })
    }

    private val repositoryClickCallback: RepositoryClickCallback = object : RepositoryClickCallback {
        override fun onClick(item: Items) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                (activity as MainActivity).show(item)
            }
        }
    }

    companion object {
        const val TAG = "RepositoryListFragment"
    }
}