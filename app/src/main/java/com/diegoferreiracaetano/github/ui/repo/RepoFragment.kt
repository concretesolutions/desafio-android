package com.diegoferreiracaetano.github.ui.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.diegoferreiracaetano.domain.repo.Repo
import com.diegoferreiracaetano.github.R
import com.diegoferreiracaetano.github.databinding.FragmentRepoBinding
import com.diegoferreiracaetano.github.ui.MainActivity
import com.diegoferreiracaetano.github.ui.pull.PullFragment
import com.diegoferreiracaetano.github.ui.repo.adapter.RepoViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoFragment : Fragment(),RepoViewHolder.OnItemClickListener{


    val viewModel: RepoViewModel by viewModel()

    private lateinit var binding: FragmentRepoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_repo, container, false)
        binding.setLifecycleOwner(this@RepoFragment)
        binding.viewModel = viewModel
        binding.callback = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).supportActionBar?.subtitle = ""
    }

    override fun onItemClick(view: View,repo: Repo) {
        val bundle = Bundle()
        bundle.putSerializable(PullFragment.EXTRA_REPO_NAME,repo.name)
        bundle.putSerializable(PullFragment.EXTRA_OWNER_NAME,repo.owner.name)
        Navigation.findNavController(view).navigate(R.id.action_next, bundle)
    }
}
