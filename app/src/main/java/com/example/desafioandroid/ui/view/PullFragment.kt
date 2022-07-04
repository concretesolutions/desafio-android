package com.example.desafioandroid.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafioandroid.R
import com.example.desafioandroid.data.network.ApiResponseStatus
import com.example.desafioandroid.databinding.FragmentPullListBinding
import com.example.desafioandroid.ui.viewmodel.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PullFragment : Fragment() {

    private lateinit var binding: FragmentPullListBinding
    private val args: PullFragmentArgs by navArgs()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPullListBinding.inflate(inflater, container, false)

        viewModel.loadPullOwner(args.owner, args.repoName)

        val recycler = binding.rvPull
        val manager = LinearLayoutManager(context)
        recycler.layoutManager = manager
        recycler.addItemDecoration(DividerItemDecoration(context, manager.orientation))
        val adapter = PullAdapter()
        recycler.adapter = adapter

        //EU no puede realizar la navegacion mediante findNavController, ya que da error por navgraph..
        adapter.setOnItemClickListener {
            /* Log.i("Test", it.toString())
             val url = URLEncoder.encode(it.html_url, StandardCharsets.UTF_8.toString())
             Log.i("Encode",url)
             val request = NavDeepLinkRequest.Builder
                 .fromUri(url.toUri())
                 .build()
             findNavController().navigate(request)*/
            val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it.html_url))
            startActivity(myIntent)
        }

        viewModel.pullModel.observe(viewLifecycleOwner) { pullList ->
            adapter.submitList(pullList)
            adapter.notifyDataSetChanged()
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigateUp()
        }

        viewModel.status.observe(viewLifecycleOwner) { status ->
            when (status) {
                ApiResponseStatus.LOADING -> {
                    binding.shimmerPull.isVisible = true
                    binding.viewContainer.isVisible= false
                }
                ApiResponseStatus.ERROR -> {
                    binding.shimmerPull.isVisible = false
                    binding.viewContainer.isVisible= false
                    dialogAlert(getString(R.string.emptyPull))
                }
                ApiResponseStatus.SUCCESS -> {
                    binding.shimmerPull.isVisible = false
                    binding.viewContainer.isVisible= true
                }
                else -> {
                    binding.shimmerPull.isVisible = false
                    binding.viewContainer.isVisible= false
                    dialogAlert(getString(R.string.emptyPull))
                }
            }
        }
        return binding.root
    }

    private fun dialogAlert(body: String) {
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(getString(R.string.emptyPullError))
                .setMessage(body)
                .setPositiveButton(
                    getString(R.string.buttonOK)
                ) { _, _ ->
                    findNavController().navigateUp()
                }
                .show()
        };
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.titlePulls)
    }

}
