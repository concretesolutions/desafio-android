package br.com.alura.javapop.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.alura.javapop.R;
import br.com.alura.javapop.model.Repositorio;

public class ListaJavaPopAdapter extends RecyclerView.Adapter<ListaJavaPopAdapter.RepositorioViewHolder>{

    private final List<Repositorio> repositorios;
    private final Context context;
    private final OnItemClickListener listener;

    public ListaJavaPopAdapter(Context context, List<Repositorio> repositorios, OnItemClickListener listener){
        this.context = context;
        this.repositorios = repositorios;
        this.listener = listener;
    }

    @Override
    public ListaJavaPopAdapter.RepositorioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_java_pop, parent, false);
        return new RepositorioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListaJavaPopAdapter.RepositorioViewHolder holder, int position) {
        Repositorio repositorio = repositorios.get(position);
        holder.vincula(repositorio, listener);
    }

    @Override
    public int getItemCount() {
        return repositorios.size();
    }

    public List<Repositorio> adiciona(List<Repositorio> repositorios){
        this.repositorios.addAll(repositorios);
        notifyDataSetChanged();
        return this.repositorios;
    }

    class RepositorioViewHolder extends RecyclerView.ViewHolder {

        private final TextView nome;
        private final TextView descricao;
        private final TextView nomeUsuario;
        private final TextView sobrenome;
        private final TextView quantidadeForks;
        private final TextView quantidadeEstrelas;

        private final ImageView avatar;

        public RepositorioViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.item_java_pop_nome_repositorio);
            descricao = itemView.findViewById(R.id.item_java_pop_descricao);
            nomeUsuario = itemView.findViewById(R.id.item_java_pop_nome_usuario);
            sobrenome = itemView.findViewById(R.id.item_java_pop_sobrenome_usuario);
            quantidadeForks = itemView.findViewById(R.id.item_java_pop_quantidade_forks);
            quantidadeEstrelas = itemView.findViewById(R.id.item_java_pop_quantidade_estrelas);
            avatar = (ImageView) itemView.findViewById(R.id.item_java_pop_imagem_usuario);
        }

        public void vincula(final Repositorio repositorio, final OnItemClickListener listener){
            nome.setText(repositorio.getNome());
            descricao.setText(repositorio.getDescricao());
            nomeUsuario.setText(repositorio.getUsuario().getNome());
            sobrenome.setText(repositorio.getUsuario().getSobrenome());
            quantidadeForks.setText(String.valueOf(repositorio.getQuantidadeForks()));
            quantidadeEstrelas.setText(String.valueOf(repositorio.getQuantidadeEstrelas()));
            Picasso.get().load(repositorio.getUsuario().getUrlAvatar()).into(avatar);
            itemView.setOnClickListener(clickRepositorio(repositorio, listener));
        }

        @NonNull
        private View.OnClickListener clickRepositorio(final Repositorio repositorio, final OnItemClickListener listener) {
            return new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(repositorio);
                }
            };
        }

    }

    public interface OnItemClickListener{
        void onItemClick(Repositorio repositorio);
    }
}
