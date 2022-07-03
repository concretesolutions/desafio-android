package com.example.desafioandroid.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.navigateUp
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafioandroid.data.network.ApiResponseStatus
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

        viewModel.status.observe(viewLifecycleOwner){ status ->
            when(status){
                ApiResponseStatus.LOADING ->{
                    binding.bprogressPull.visibility = View.VISIBLE
                }
                ApiResponseStatus.ERROR -> {
                    binding.bprogressPull.visibility = View.GONE
                    Toast.makeText(context, "Repositorio no tiene Pull, sera redirigido al listado", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                }
                ApiResponseStatus.SUCCESS -> {
                    binding.bprogressPull.visibility = View.GONE
                }
                else ->{
                    binding.bprogressPull.visibility = View.GONE
                    Toast.makeText(context, "Error, agregar Alert desconocido", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                }
            }
        }
        return binding.root
    }
}
