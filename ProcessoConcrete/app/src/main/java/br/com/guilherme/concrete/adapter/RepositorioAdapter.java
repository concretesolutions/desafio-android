package br.com.guilherme.concrete.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.guilherme.concrete.R;
import br.com.guilherme.concrete.model.Repositorio;
import br.com.guilherme.concrete.view.RepositorioActivity;

public class RepositorioAdapter extends RecyclerView.Adapter<RepositorioAdapter.ViewHolder> {
    private List<Repositorio> listRepositorios;
    private Context context;

    public RepositorioAdapter(List<Repositorio> listRepositorios, Context context) {
        this.listRepositorios = listRepositorios;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_repositories, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.containerInfos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(RepositorioActivity.getIntent(context));
            }
        });

        holder.nomeRepositorio.setText(listRepositorios.get(position).getNomeRepositorio());
//        holder.descricaoRepositorio.setText(listRepositorios.get(position).getDescricaoRespositorio());
//        holder.qtdBranches.setText(listRepositorios.get(position).getQtdBranches());
//        holder.qtdFavoritos.setText(listRepositorios.get(position).getQtdFavoritos());
    }

    @Override
    public int getItemCount() {
        return listRepositorios.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout containerInfos;
        TextView nomeRepositorio;
        TextView descricaoRepositorio;
        TextView qtdBranches;
        TextView qtdFavoritos;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            containerInfos = itemView.findViewById(R.id.row);
            nomeRepositorio = itemView.findViewById(R.id.nome_repositorio);
            descricaoRepositorio = itemView.findViewById(R.id.descricao_repositorio);
            qtdBranches = itemView.findViewById(R.id.info_branches);
            qtdFavoritos = itemView.findViewById(R.id.info_favoritos);
        }
    }
}


