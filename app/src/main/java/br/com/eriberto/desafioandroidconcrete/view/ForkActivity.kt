package br.com.eriberto.desafioandroidconcrete.view

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import br.com.eriberto.desafioandroidconcrete.R
import br.com.eriberto.desafioandroidconcrete.model.ForkModel
import br.com.eriberto.desafioandroidconcrete.model.interfaces.ForkSearchView
import br.com.eriberto.desafioandroidconcrete.model.pojo.ForkRepositorio
import br.com.eriberto.desafioandroidconcrete.presenter.ForkSearchPresenter

import kotlinx.android.synthetic.main.activity_fork.*

class ForkActivity : AppCompatActivity(), ForkSearchView {
    private val presenter = ForkSearchPresenter(view = this, model = ForkModel)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fork)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showResult(result: ArrayList<ForkRepositorio>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErroResult(mensagem: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
