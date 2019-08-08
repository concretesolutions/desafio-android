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
import br.com.eriberto.desafioandroidconcrete.model.interfaces.InteracaoComListaFork
import br.com.eriberto.desafioandroidconcrete.model.pojo.ForkRepositorio
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_fork.view.*

class AdapterListaFork(
    private val context: Context,
    private val mValues: ArrayList<ForkRepositorio>,
    private val interacaoComLista: InteracaoComListaFork
) : RecyclerView.Adapter<AdapterListaFork.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener = View.OnClickListener { v ->
        val fork = v.tag as ForkRepositorio

        interacaoComLista.selecionou(fork)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fork, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mValues.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fork: ForkRepositorio = mValues[position]

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
        val ivAvatarUser: ImageView = mView.ivAvatarUser
    }
}