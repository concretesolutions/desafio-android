package com.ccortez.desafioconcrete.ui.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ccortez.desafioconcrete.R
import com.ccortez.desafioconcrete.databinding.FragmentPullListBinding
import com.ccortez.desafioconcrete.di.Injectable
import com.ccortez.desafioconcrete.model.PullRequest
import com.ccortez.desafioconcrete.ui.base.BaseFragment
import com.ccortez.desafioconcrete.ui.callback.RepositoryPullsItemClickCallback
import com.ccortez.desafioconcrete.utils.viewModelOf
import dagger.android.support.AndroidSupportInjection

class RepositoryFragment : BaseFragment<RepositoryViewModel, FragmentPullListBinding>() {

//    private lateinit var binding: FragmentPullListBinding
    var mActionMode: ActionMode? = null

    private var mContext: Context? = null
    private lateinit var repositoryPullsAdapter: RepositoryPullsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // Inflate this data binding layout
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pull_list,
//            container, false)
        repositoryPullsAdapter = RepositoryPullsAdapter(repositoryPullsClickCallback)
        mViewBinding!!.pullsItems.adapter = repositoryPullsAdapter
        mActionMode =
            activity?.startActionMode(mActionModeCallback)
        mContext = activity?.applicationContext
        // Create and set the adapter for the RecyclerView.
        return mViewBinding.getRoot()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        val viewModel = ViewModelProviders.of(this,
//            viewModelFactory)
//            .get(RepositoryViewModel::class.java)
        val viewModel = mViewModel

        viewModel.repositoryID(requireArguments().getString(KEY_REPOSITORY_ID))
        mViewBinding.isLoading = true

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: RepositoryViewModel) { // Observe car data
        viewModel.observableCar.observe(viewLifecycleOwner, Observer { car ->
            if (car != null) {
                mViewBinding!!.isLoading = false
                mViewBinding!!.tvEmptyPullList.text = ""
                repositoryPullsAdapter!!.setItems(car)
//                binding!!.openedClosed.text = car.
                if (repositoryPullsAdapter!!.pullsItems!!.isEmpty()) {
                    mViewBinding!!.tvEmptyPullList.text = getString(R.string.empty_pulls)
                }
            }
        })
    }

    override fun getViewModel(): RepositoryViewModel = viewModelOf<RepositoryViewModel>(mViewModelProvider)

    override fun getViewBinding(): FragmentPullListBinding = FragmentPullListBinding.inflate(layoutInflater)

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