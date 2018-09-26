package br.com.alura.javapop.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.alura.javapop.R;
import br.com.alura.javapop.model.Repositorio;

public class ListaJavaPopAdapter extends RecyclerView.Adapter<ListaJavaPopAdapter.RepositorioViewHolder>{

    private final List<Repositorio> repositorios;
    private final Context context;

    public ListaJavaPopAdapter(Context context, List<Repositorio> repositorios){
        this.context = context;
        this.repositorios = repositorios;
    }

    @Override
    public ListaJavaPopAdapter.RepositorioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_java_pop, parent, false);
        return new RepositorioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListaJavaPopAdapter.RepositorioViewHolder holder, int position) {
        Repositorio repositorio = repositorios.get(position);
        holder.vincula(repositorio);
    }

    @Override
    public int getItemCount() {
        return repositorios.size();
    }

    class RepositorioViewHolder extends RecyclerView.ViewHolder {

        private final TextView nome;
        private final TextView descricao;
        private final TextView nomeUsuario;
        private final TextView sobrenome;
        private final TextView quantidadeForks;
        private final TextView quantidadeEstrelas;

        public RepositorioViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.item_java_pop_nome_repositorio);
            descricao = itemView.findViewById(R.id.item_java_pop_descricao);
            nomeUsuario = itemView.findViewById(R.id.item_java_pop_nome_usuario);
            sobrenome = itemView.findViewById(R.id.item_java_pop_sobrenome_usuario);
            quantidadeForks = itemView.findViewById(R.id.item_java_pop_quantidade_forks);
            quantidadeEstrelas = itemView.findViewById(R.id.item_java_pop_quantidade_estrelas);
        }

        public void vincula(Repositorio repositorio){
            preencheCampo(repositorio);
        }

        private void preencheCampo(Repositorio repositorio) {
            nome.setText(repositorio.getNome());
            descricao.setText(repositorio.getDescricao());
            nomeUsuario.setText(repositorio.getNomeUsuario());
            sobrenome.setText(repositorio.getSobrenomeUsuario());
            quantidadeForks.setText(String.valueOf(repositorio.getQuantidadeForks()));
            quantidadeEstrelas.setText(String.valueOf(repositorio.getQuantidadeEstrelas()));
        }
    }

    public void adiciona(List<Repositorio> repositorios){
        this.repositorios.addAll(repositorios);
        notifyDataSetChanged();
    }
}
