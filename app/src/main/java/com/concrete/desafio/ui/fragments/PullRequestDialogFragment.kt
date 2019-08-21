package com.concrete.desafio.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.concrete.desafio.R
import com.concrete.desafio.ui.PullRequestAdapter
import com.concrete.desafio.viewmodels.PullRequestViewModel
import kotlinx.android.synthetic.main.fragment_pull_resquest_dialog.*
import org.koin.android.viewmodel.ext.android.viewModel

class PullRequestDialogFragment() : DialogFragment() {


    private val pullRequestViewModel: PullRequestViewModel by viewModel()

    val pullRequestAdapter: PullRequestAdapter by lazy {
        PullRequestAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pull_resquest_dialog, container)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        val toolbar: androidx.appcompat.widget.Toolbar = view.findViewById(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        toolbar.title = arguments!!.getString("repositorio")!!

        toolbar.setNavigationOnClickListener {
           dismiss()
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = pullRequestAdapter

        pullRequestViewModel.getPullRequest()?.observe(this, Observer{ data ->

            progress_center.visibility = View.GONE

            data?.let{
                if(it.isEmpty()){
                    Toast.makeText(context, "Lista vazia!", Toast.LENGTH_LONG).show()
                } else {
                    pullRequestAdapter.add(it)
                }
            }
        })

        pullRequestViewModel.buscarPullRequest(arguments!!.getString("repositorio")!!, arguments!!.getString("autor")!!)

    }

    companion object {
        fun newInstance(repositorio: String, autor: String): PullRequestDialogFragment {

            val frag = PullRequestDialogFragment()
            val args = Bundle()
            args.putString("repositorio", repositorio)
            args.putString("autor", autor)
            frag.arguments = args
            return frag
        }
    }

    override fun onResume() {
        super.onResume()
        val window = dialog!!.window
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.CENTER)
    }

}