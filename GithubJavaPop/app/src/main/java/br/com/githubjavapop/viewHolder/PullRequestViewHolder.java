package br.com.githubjavapop.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.githubjavapop.R;

public class PullRequestViewHolder extends RecyclerView.ViewHolder {

    public TextView titulo;
    public TextView data;
    public TextView body;
    public TextView username;
    public ImageView foto;

    public PullRequestViewHolder(@NonNull View itemView) {
        super(itemView);

        titulo = itemView.findViewById(R.id.textoTitulo);
        data = itemView.findViewById(R.id.textoData);
        body = itemView.findViewById(R.id.textoBody);
        username = itemView.findViewById(R.id.textoUsernamePull);
        foto = itemView.findViewById(R.id.imagemUsuarioPull);
    }
}
