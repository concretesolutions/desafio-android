package br.edu.ifsp.scl.desafio_android.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifsp.scl.desafio_android.R
import br.edu.ifsp.scl.desafio_android.adapter.PullsAdapter
import br.edu.ifsp.scl.desafio_android.api.PullService
import br.edu.ifsp.scl.desafio_android.api.RetrofitClient
import br.edu.ifsp.scl.desafio_android.model.Pull
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_pull_list.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PullListFragment : Fragment() {

    // interfaces
    private var pService: PullService? = null
    // model
    private var pulls: List<Pull> = listOf()
    // layouts
    private var linearLayoutManager: LinearLayoutManager? = null
    // utils
    private var login: String = ""
    private var repo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pService = RetrofitClient().getClient(context!!).create(PullService::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_pull_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Pull requests"
        login = arguments?.get("login") as String
        repo = arguments?.get("repo") as String
        loadPage()
    }

    fun loadPage(){
        pService?.getPullRequest(login, repo)?.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                pb_pull.visibility = View.GONE
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                pulls = Gson().fromJson((response.body()?.string()), Array<Pull>::class.java).toList()
                bind()
            }
        })
    }

    fun bind() {
        pb_pull.visibility = View.GONE
        if(pulls.isNotEmpty()) {
            linearLayoutManager = LinearLayoutManager(context)
            tv_pull_vazio.visibility = View.GONE
            rv_pull.apply {
                layoutManager = linearLayoutManager
                adapter = PullsAdapter(context!!, pulls) {
                    val openURL = Intent(Intent.ACTION_VIEW)
                    openURL.data = Uri.parse("${pulls[it].html_url}")
                    startActivity(openURL)
                }
            }
        } else {
            tv_pull_vazio.visibility = View.VISIBLE
        }
    }
}
