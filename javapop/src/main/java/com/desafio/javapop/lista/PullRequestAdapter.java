package com.desafio.javapop.lista;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.desafio.javapop.R;
import com.desafio.javapop.model.PullRequest;

import java.util.List;

public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.PullViewHolder> {
    List<PullRequest> pullRequests;

    public PullRequestAdapter(List<PullRequest> pullRequests) {
        this.pullRequests = pullRequests;
    }

    @NonNull
    @Override
    public PullViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View viewTest =  LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_itens_pull, parent, false);
        PullViewHolder viewHolder = new PullViewHolder(viewTest);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PullViewHolder holder, int position) {

        PullRequest pullRequest = pullRequests.get(position);

        holder.txtTitulo.setText(pullRequests.get(position).getTitulo());
        holder.txtCorpo.setText(pullRequest.getCorpo());
        holder.txtData.setText(pullRequest.getData());
        holder.txtUsuario.setText(pullRequest.getUsuario().getUsuario());

        Glide.with(holder.itemView.getContext())
                .load("https://avatars0.githubusercontent.com/u/" + pullRequest.getUsuario().getId() + "?v=4" + ".png")
                .placeholder(R.drawable.ic_launcher_foreground)
                .circleCrop()
                .into(holder.imgUsuario);
    }

    @Override
    public int getItemCount() { return pullRequests.size(); }

    class PullViewHolder extends RecyclerView.ViewHolder{

        TextView txtTitulo,txtCorpo,txtData,txtUsuario;
        ImageView imgUsuario;

        public PullViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitulo = (TextView) itemView.findViewById(R.id.txtTitulo);
            txtCorpo = (TextView) itemView.findViewById(R.id.txtCorpo);
            txtData = (TextView) itemView.findViewById(R.id.txtData);
            txtUsuario = (TextView) itemView.findViewById(R.id.txtUsuario);
            imgUsuario = (ImageView) itemView.findViewById(R.id.imgUsuario);
        }
    }
}
