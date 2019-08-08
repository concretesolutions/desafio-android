package br.com.eriberto.desafioandroidconcrete.view.recyclerViewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.eriberto.desafioandroidconcrete.R
import br.com.eriberto.desafioandroidconcrete.model.interfaces.InteracaoComLista
import br.com.eriberto.desafioandroidconcrete.model.pojo.Repositorio

class AdapterListaRepository(
    private val mValues: ArrayList<Repositorio>,
    private val interacaoComLista: InteracaoComLista
) : RecyclerView.Adapter<AdapterListaRepository.ViewHolder>() {

    private var numeroPagina: Int = 0
    private var quantidadeDeitensAdicionados: Int = 0

    fun setLista(novaLista: ArrayList<Repositorio>) {
        novaLista.forEach {
            mValues.add(it)
        }
        quantidadeDeitensAdicionados = novaLista.size
        notifyDataSetChanged()
    }

    fun setNumeroDaPaginaAtual(numeroDaPagina: Int) {
        numeroPagina = numeroDaPagina
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repositorio, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mValues.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produtoLoja = mValues[position]
    }


    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {

    }
}