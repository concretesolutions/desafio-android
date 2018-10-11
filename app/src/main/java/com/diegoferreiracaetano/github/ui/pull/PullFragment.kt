package com.diegoferreiracaetano.github.ui.pull


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.diegoferreiracaetano.domain.pull.Pull
import com.diegoferreiracaetano.github.R
import com.diegoferreiracaetano.github.databinding.FragmentPullBinding
import com.diegoferreiracaetano.github.ui.MainActivity
import com.diegoferreiracaetano.github.ui.pull.adapter.PullViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel


class PullFragment : Fragment(),PullViewHolder.OnItemClickListener {
    val viewModel: PullViewModel by viewModel()

    private lateinit var binding: FragmentPullBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_pull, container, false)
        binding.setLifecycleOwner(this@PullFragment)
        binding.viewModel = viewModel
        binding.callback = this
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val repoName = arguments!!.getString(EXTRA_REPO_NAME)
            val ownerName = arguments!!.getString(EXTRA_OWNER_NAME)
            (activity as MainActivity).supportActionBar?.subtitle = repoName?.capitalize()
            viewModel.setParams(Pair(ownerName,repoName))
        }
    }

    override fun onItemClick(view: View, pull: Pull) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(pull.url))
        startActivity(browserIntent)
    }


    companion object {
        val EXTRA_REPO_NAME = "EXTRA_REPO_NAME"
        val EXTRA_OWNER_NAME = "EXTRA_OWNER_NAME"
    }
}
