package com.concretesolutions.diegosouza.concretesolutions.View

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AlertDialog
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

    private var login: String? = null
    private var nome: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        title = "Pulls"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        login = intent.getStringExtra("login")
        nome = intent.getStringExtra("nome")

        carregarLista()

        progressBar2.visibility = View.VISIBLE
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun carregarLista() {

        val call = login?.let { nome?.let { it1 -> RetrofitClient().listaService().listaPulls(it, it1) } }
        call?.enqueue(object : Callback<List<InformationList>> {
            override fun onResponse(call: Call<List<InformationList>>, response: Response<List<InformationList>>) {
                response.body()?.let {

                    val lista: List<InformationList> = it
                    configurarListagem(lista)

                    title = "Pulls open: " + lista.size
                }
            }

            override fun onFailure(call: Call<List<InformationList>>, t: Throwable) {
                progressBar2.visibility = View.GONE
                alertDialog()
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

    fun alertDialog() {

        val builder = AlertDialog.Builder(this@InformationActivity)
        builder.setTitle("Atenção")

        builder.setMessage("Desculpe, não conseguimos concluir a requisição")

        builder.setPositiveButton("Ok") { dialog, which ->
            val intent = Intent(this@InformationActivity, MainActivity::class.java);
            startActivity(intent)
        }

        val dialog: AlertDialog = builder.create()

        dialog.show()
    }
}
