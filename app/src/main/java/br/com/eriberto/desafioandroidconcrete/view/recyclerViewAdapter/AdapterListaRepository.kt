package br.com.eriberto.desafioandroidconcrete.view.recyclerViewAdapter

import android.annotation.SuppressLint
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
import br.com.eriberto.desafioandroidconcrete.model.pojo.Repositorio
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_repositorio.view.*
import java.io.Serializable

class AdapterListaRepository(
    private val context:Context,
    private val mValues: ArrayList<Repositorio>,
    private val interacaoComLista: InteracaoComLista
) : RecyclerView.Adapter<AdapterListaRepository.ViewHolder>(), Serializable {

    private var numeroPagina: Int = 0
    private var quantidadeDeitensAdicionados: Int = 0

    init {
        quantidadeDeitensAdicionados = mValues.size
    }

    private val mOnClickListener: View.OnClickListener = View.OnClickListener { v ->
        val repositorio = v.tag as Repositorio

        interacaoComLista.selecionou(repositorio)

    }

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

    fun getList(): ArrayList<Repositorio>{
        return mValues
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repositorio, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mValues.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repositorio: Repositorio = mValues[position]

        if ((position + 1) == mValues.size) //se a quantidades de itens renderizados for iqual a quantidade de itens da lista
            if (quantidadeDeitensAdicionados == 30)
                interacaoComLista.buscarMais(numeroDaPagina = numeroPagina+1)


        Picasso.get()
            .load(repositorio.proprietario.avatar_url)
            //.memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .resize(100, 100)
            .centerCrop()
            .placeholder(ContextCompat.getDrawable(context, R.mipmap.image_placeholder_350x350)!!)
            .into(holder.imagemAvatar)

        holder.tvDescricaoRepositorio.text = repositorio.descricaoRepositorio
        holder.tvNomeRepositorio.text = repositorio.nomeRepositorio
        holder.tvQuantidadeFork.text = repositorio.quantidadeDeForks.toString()
        holder.tvQuantidadeStar.text = repositorio.quantidadeDeEstrelas.toString()
        holder.tvNomeUsuario.text = repositorio.proprietario.nomeAutor

        with(holder.mView) {
            tag = repositorio
            setOnClickListener(mOnClickListener)
        }
    }


    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val tvNomeRepositorio: TextView = mView.tvNomeRepositorio
        val tvDescricaoRepositorio: TextView = mView.tvDescricaoRepositorio
        val tvNomeUsuario: TextView = mView.tvNomeUsuario
        val tvQuantidadeFork: TextView = mView.tvQuantidadeFork
        val tvQuantidadeStar: TextView = mView.tvQuantidadeStar
        val imagemAvatar:ImageView = mView.imageAvatar
    }
}