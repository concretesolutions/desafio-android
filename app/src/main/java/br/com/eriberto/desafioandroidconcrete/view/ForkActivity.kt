package br.com.eriberto.desafioandroidconcrete.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView
import br.com.eriberto.desafioandroidconcrete.R
import br.com.eriberto.desafioandroidconcrete.model.ForkModel
import br.com.eriberto.desafioandroidconcrete.model.interfaces.ForkSearchView
import br.com.eriberto.desafioandroidconcrete.model.interfaces.InteracaoComListaFork
import br.com.eriberto.desafioandroidconcrete.model.pojo.ForkRepositorio
import br.com.eriberto.desafioandroidconcrete.model.pojo.Repositorio
import br.com.eriberto.desafioandroidconcrete.presenter.ForkSearchPresenter
import br.com.eriberto.desafioandroidconcrete.view.recyclerViewAdapter.AdapterListaFork

import kotlinx.android.synthetic.main.activity_fork.*
import kotlinx.android.synthetic.main.content_fork.*

class ForkActivity : AppCompatActivity(), ForkSearchView {
    private val presenter = ForkSearchPresenter(view = this, model = ForkModel)

    lateinit var repositorio: Repositorio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fork)

        if (intent.hasExtra("repositorio"))
            repositorio = intent.getSerializableExtra("repositorio") as Repositorio

        toolbar.title = repositorio.nomeRepositorio
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (recyclerViewForks is RecyclerView)
            with(recyclerViewForks) {
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@ForkActivity)
            }

        presenter.search(
            nomeProprietario = repositorio.proprietario.nomeAutor,
            nomeRepositorio = repositorio.nomeRepositorio
        )

        swipeRefresh_fork.setOnRefreshListener {
            presenter.search(
                nomeProprietario = repositorio.proprietario.nomeAutor,
                nomeRepositorio = repositorio.nomeRepositorio
            )
        }
    }

    override fun showProgress() {
        swipeRefresh_fork.isRefreshing = true
    }

    override fun hideProgress() {
        swipeRefresh_fork.isRefreshing = false
    }

    override fun showResult(result: ArrayList<ForkRepositorio>) {
        recyclerViewForks.adapter = AdapterListaFork(
            context = this,
            mValues = result,
            interacaoComLista = object : InteracaoComListaFork {
                override fun selecionou(fork: ForkRepositorio) {
                    startActivity(
                        Intent(Intent.ACTION_VIEW).setData(Uri.parse(fork.cabecalho.repositorio.htmlSite))
                    )
                }

                override fun buscarMais(numeroDaPagina: Int) {
                    presenter.search(
                        nomeProprietario = repositorio.proprietario.nomeAutor,
                        nomeRepositorio = repositorio.nomeRepositorio
                    )
                }
            })


    }

    override fun showErroResult(mensagem: String?) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
    }

}
