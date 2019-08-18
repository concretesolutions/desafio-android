package com.desafio.javapop.lista;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.desafio.javapop.R;
import com.desafio.javapop.model.Repositorio;
import com.desafio.javapop.view.PullActivity;

import java.util.List;

public class RepositorioAdapter extends RecyclerView.Adapter<RepositorioAdapter.MyViewHolder>{
    List<Repositorio> repositorios;
    Context context;

    public RepositorioAdapter(List<Repositorio> repositorios, Context context) {
        this.repositorios = repositorios;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_itens_repo, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Repositorio repositorio = repositorios.get(position);

        holder.txtNomeRepo.setText(repositorio.getNome());
        holder.txtDescRepo.setText(repositorio.getDescricao());
        holder.txtQtdFork.setText(repositorio.getFork().toString());
        holder.txtQtdStart.setText(repositorio.getStart().toString());
        holder.txtUsuario.setText(repositorio.getUsuario().getUsuario());

        Glide.with(context)
                .load("https://avatars0.githubusercontent.com/u/" + repositorio.getUsuario().getId() + "?v=4" + ".png")
                .placeholder(R.drawable.ic_launcher_foreground)
                .apply(RequestOptions.circleCropTransform())
                .circleCrop()
                .into(holder.imgUsuario);

        holder.layoutRepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PullActivity.class);
                intent.putExtra("nome",repositorio.getNome());
                intent.putExtra("login",repositorio.getUsuario().getUsuario());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return repositorios.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtNomeRepo,txtDescRepo,txtQtdFork,txtQtdStart,txtUsuario;
        ImageView imgUsuario;
        LinearLayout layoutRepo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNomeRepo = (TextView) itemView.findViewById(R.id.txtNomeRepo);
            txtDescRepo = (TextView) itemView.findViewById(R.id.txtDescRepo);
            txtQtdFork = (TextView) itemView.findViewById(R.id.txtQtdFork);
            txtQtdStart = (TextView) itemView.findViewById(R.id.txtQtdStart);
            txtUsuario = (TextView) itemView.findViewById(R.id.txtUsuario);
            imgUsuario = (ImageView) itemView.findViewById(R.id.imgUsuario);
            layoutRepo = (LinearLayout) itemView.findViewById(R.id.ltPrincipal);
        }
    }
}
