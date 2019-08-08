package br.com.eriberto.desafioandroidconcrete.view.recyclerViewAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.eriberto.desafioandroidconcrete.R
import br.com.eriberto.desafioandroidconcrete.model.interfaces.InteracaoComLista
import br.com.eriberto.desafioandroidconcrete.model.interfaces.InteracaoComListaFork
import br.com.eriberto.desafioandroidconcrete.model.pojo.ForkRepositorio
import br.com.eriberto.desafioandroidconcrete.model.pojo.Repositorio
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_fork.view.*
import kotlinx.android.synthetic.main.item_repositorio.view.*

class AdapterListaFork(
    private val context: Context,
    private val mValues: ArrayList<ForkRepositorio>,
    private val interacaoComLista: InteracaoComListaFork
) : RecyclerView.Adapter<AdapterListaFork.ViewHolder>() {

    private var numeroPagina: Int = 0
    private var quantidadeDeitensAdicionados: Int = 0

    init {
        quantidadeDeitensAdicionados = mValues.size
    }

    private val mOnClickListener: View.OnClickListener = View.OnClickListener { v ->
        val repositorio = v.tag as ForkRepositorio

        interacaoComLista.selecionou(repositorio)

    }

    fun setLista(novaLista: ArrayList<ForkRepositorio>) {
        novaLista.forEach {
            mValues.add(it)
        }
        quantidadeDeitensAdicionados = novaLista.size
        notifyDataSetChanged()
    }

    fun setNumeroDaPaginaAtual(numeroDaPagina: Int) {
        numeroPagina = numeroDaPagina
    }

    fun getList(): ArrayList<ForkRepositorio> {
        return mValues
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fork, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mValues.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fork: ForkRepositorio = mValues[position]

        if ((position + 1) == mValues.size) //se a quantidades de itens renderizados for iqual a quantidade de itens da lista
            if (quantidadeDeitensAdicionados == 30)
                interacaoComLista.buscarMais(numeroDaPagina = numeroPagina + 1)


        Picasso.get()
            .load(fork.autorPR.urlFoto)
            //.memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .resize(100, 100)
            .centerCrop()
            .placeholder(ContextCompat.getDrawable(context, R.mipmap.image_placeholder_350x350)!!)
            .into(holder.ivAvatarUser)

        holder.tvDescricaoFork.text = fork.descricao
        holder.tvNomeUser.text = fork.autorPR.nome
        holder.tvTituloFork.text = fork.titulo
//        holder.tvNomeSobrenomeUser.text = fork.quantidadeDeEstrelas.toString()
//        holder.tvNomeUsuario.text = fork.proprietario.nomeAutor

        with(holder.mView) {
            tag = fork
            setOnClickListener(mOnClickListener)
        }
    }


    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val tvTituloFork: TextView = mView.tvTituloFork
        val tvDescricaoFork: TextView = mView.tvDescricaoFork
        val tvNomeUser: TextView = mView.tvNomeUser
        val tvNomeSobrenomeUser: TextView = mView.tvNomeSobrenomeUser
        val ivAvatarUser:ImageView = mView.ivAvatarUser
    }
}