package br.com.guilherme.concrete.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.com.guilherme.concrete.R;
import br.com.guilherme.concrete.model.Repositorio;
import br.com.guilherme.concrete.presenter.RepositorioPresenter;
import br.com.guilherme.concrete.view.PullRequestActivity;

public class RepositorioAdapter extends RecyclerView.Adapter<RepositorioAdapter.ViewHolder> {
    private List<Repositorio> listRepositorios;
    private Context context;
    private RepositorioPresenter presenter;

    public RepositorioAdapter(List<Repositorio> listRepositorios, Context context, RepositorioPresenter presenter) {
        this.listRepositorios = listRepositorios;
        this.context = context;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_repositories, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.containerInfos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = PullRequestActivity.getIntent(context);
                intent.putExtra("nomeUsuario", listRepositorios.get(position).getUser().getNomeUsuario());
                intent.putExtra("nomeRepositorio", listRepositorios.get(position).getNomeRepositorio());
                context.startActivity(intent);
            }
        });

        holder.nomeRepositorio.setText(listRepositorios.get(position).getNomeRepositorio());
        holder.descricaoRepositorio.setText(listRepositorios.get(position).getDescricaoRespositorio());
        holder.qtdBranches.setText(String.valueOf(listRepositorios.get(position).getQtdBranches()));
        holder.qtdFavoritos.setText(String.valueOf(listRepositorios.get(position).getQtdFavoritos()));

        holder.username.setText(listRepositorios.get(position).getUser().getNomeUsuario());
        Glide.with(context).load(listRepositorios.get(position).getUser().getFotoUsuario()).into(holder.avatarUser);
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

        TextView username;
        ImageView avatarUser;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            containerInfos = itemView.findViewById(R.id.row);
            nomeRepositorio = itemView.findViewById(R.id.nome_repositorio);
            descricaoRepositorio = itemView.findViewById(R.id.descricao_repositorio);
            qtdBranches = itemView.findViewById(R.id.info_branches);
            qtdFavoritos = itemView.findViewById(R.id.info_favoritos);

            username = itemView.findViewById(R.id.username);
            avatarUser = itemView.findViewById(R.id.foto_user);
        }
    }
}


