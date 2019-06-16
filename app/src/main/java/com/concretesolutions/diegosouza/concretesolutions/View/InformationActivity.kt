package com.concretesolutions.diegosouza.concretesolutions.View

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.concretesolutions.diegosouza.concretesolutions.Adapter.InformationAdapter
import com.concretesolutions.diegosouza.concretesolutions.Api.RetrofitClient
import com.concretesolutions.diegosouza.concretesolutions.Model.InformationList
import com.concretesolutions.diegosouza.concretesolutions.R
import kotlinx.android.synthetic.main.activity_information.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InformationActivity : AppCompatActivity() {

    private lateinit var adapter: InformationAdapter

    private var full_name_pulls = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        title = ""

        full_name_pulls = intent.getStringExtra("full_name_pulls")

        carregarLista()

        progressBar2.visibility = View.VISIBLE
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun carregarLista() {

        val call = RetrofitClient().listaService().listaPulls()
        call.enqueue(object : Callback<List<InformationList>> {
            override fun onResponse(call: Call<List<InformationList>>, response: Response<List<InformationList>>) {
                response.body()?.let {

                    val lista: List<InformationList> = it
                    configurarListagem(lista)
                }
            }

            override fun onFailure(call: Call<List<InformationList>>, t: Throwable) {
                progressBar2.visibility = View.GONE
            }
        })
    }

    private fun configurarListagem(lista: List<InformationList>) {

        val recyclerView = recyclerViewInformation
        adapter = InformationAdapter(this)

        recyclerView.adapter = adapter

        adapter.setList(lista as ArrayList<InformationList>)

        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager

        progressBar2.visibility = View.GONE

        adapter.setOnItemClickListener(object : InformationAdapter.ClickListener {
            override fun onClick(pos: Int, aView: View) {

                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data = Uri.parse(lista[pos].html_url)
                startActivity(openURL)
            }
        })

    }
}
