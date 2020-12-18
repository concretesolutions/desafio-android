package com.ccortez.desafioconcreteapplication.view.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ccortez.desafioconcreteapplication.R
import com.ccortez.desafioconcreteapplication.databinding.FragmentPullListBinding
import com.ccortez.desafioconcreteapplication.di.Injectable
import com.ccortez.desafioconcreteapplication.service.model.PullRequest
import com.ccortez.desafioconcreteapplication.view.adapter.RepositoryPullsAdapter
import com.ccortez.desafioconcreteapplication.view.callback.RepositoryPullsItemClickCallback
import com.ccortez.desafioconcreteapplication.viewmodel.RepositoryViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class RepositoryFragment : Fragment(), Injectable {

    private lateinit var repositoryPullsAdapter: RepositoryPullsAdapter
    private lateinit var binding: FragmentPullListBinding
    var mActionMode: ActionMode? = null

    var viewModelFactory: ViewModelProvider.Factory? = null
        @Inject set

    private var mContext: Context? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // Inflate this data binding layout
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pull_list, container, false)
        repositoryPullsAdapter = RepositoryPullsAdapter(repositoryPullsClickCallback)
        binding!!.pullsItems.adapter = repositoryPullsAdapter
        mActionMode =
            activity?.startActionMode(mActionModeCallback)
        mContext = activity?.applicationContext
        // Create and set the adapter for the RecyclerView.
        return binding.getRoot()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(RepositoryViewModel::class.java)

        viewModel.repositoryID(requireArguments().getString(KEY_REPOSITORY_ID))
        binding!!.isLoading = true

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: RepositoryViewModel) { // Observe car data
        viewModel.observableCar.observe(viewLifecycleOwner, Observer { car ->
            if (car != null) {
                binding!!.isLoading = false
                binding!!.tvEmptyPullList.text = ""
                repositoryPullsAdapter!!.setItems(car)
//                binding!!.openedClosed.text = car.
                if (repositoryPullsAdapter!!.pullsItems!!.isEmpty()) {
                    binding!!.tvEmptyPullList.text = getString(R.string.empty_pulls)
                }
            }
        })
    }

    private val mActionModeCallback: ActionMode.Callback =
        object : ActionMode.Callback {
            override fun onCreateActionMode(
                mode: ActionMode,
                menu: Menu
            ): Boolean { // Inflate a menu resource providing context menu items
                val inflater = mode.menuInflater
                for (i in 0 until menu.size()) {
                    menu.getItem(i).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER)
                }
                inflater.inflate(R.menu.menu_no_items, menu)
                return true
            }

            override fun onPrepareActionMode(
                mode: ActionMode,
                menu: Menu
            ): Boolean {
                mode.title = requireArguments().getString(KEY_REPOSITORY_ID)
                return true
            }

            override fun onActionItemClicked(
                mode: ActionMode,
                item: MenuItem
            ): Boolean { // ver onPositiveClick para ação de cada um
                return when (item.itemId) {
                    else -> false
                }
            }

            override fun onDestroyActionMode(mode: ActionMode) {
                mActionMode = null
                if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                    (activity as MainActivity?)!!.showHome()
                }
            }
        }

    private val repositoryPullsClickCallback: RepositoryPullsItemClickCallback = object :
        RepositoryPullsItemClickCallback {
        override fun onClick(item: PullRequest) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(item.html_url))
                startActivity(browserIntent)
            }
        }

    }

    companion object {
        const val TAG = "RepositoryFragment"
        private const val KEY_REPOSITORY_ID = "full_name"
        /**
         * Creates car fragment for specific car ID
         */
        fun forRepository(full_name: String?): RepositoryFragment {
            val fragment = RepositoryFragment()
            val args = Bundle()
            args.putString(KEY_REPOSITORY_ID, full_name)
            fragment.arguments = args
            return fragment
        }
    }
}