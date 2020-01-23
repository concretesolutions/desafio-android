package com.example.gitbusca.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gitbusca.R;
import com.example.gitbusca.activity.MainActivity;
import com.example.gitbusca.helper.CircleTransform;
import com.example.gitbusca.model.RepositorioGit;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterRepositoriosGit extends RecyclerView.Adapter<AdapterRepositoriosGit.MyViewHolder> {

    private List<RepositorioGit> listRepositorios;
    private MainActivity activity;

    public AdapterRepositoriosGit(List<RepositorioGit> listRepositorios, MainActivity activity) {
        this.listRepositorios = listRepositorios;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View lista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_lista_repositorio_git, parent, false);

        return new MyViewHolder( lista );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RepositorioGit repositorio = listRepositorios.get( position );

        holder.repositorio.setText( repositorio.getName() );
        holder.descricao.setText( repositorio.getDescription() );
        holder.nome.setText( repositorio.getFull_name() );
        holder.usuario.setText( repositorio.getLogin() );
        holder.star.setText( String.valueOf(repositorio.getStargazers_count()) );
        holder.fork.setText( String.valueOf(repositorio.getForks_count()) );

        Picasso.with(activity)
                .load( repositorio.getAvatar_url() )
                .transform( new CircleTransform() )
                .into( holder.imageUsuario );
    }

    @Override
    public int getItemCount() {
        return listRepositorios.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView repositorio;
        TextView descricao;
        TextView usuario;
        TextView nome;
        TextView star;
        TextView fork;
        ImageView imageUsuario;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            repositorio = itemView.findViewById(R.id.textTitulo);
            descricao = itemView.findViewById(R.id.textDescPull);
            usuario = itemView.findViewById(R.id.textNomeUsuario);
            nome = itemView.findViewById(R.id.textNomeCompleto);
            star = itemView.findViewById(R.id.textStar);
            fork = itemView.findViewById(R.id.textFork);
            imageUsuario = itemView.findViewById(R.id.imageUser);
        }
    }
}
