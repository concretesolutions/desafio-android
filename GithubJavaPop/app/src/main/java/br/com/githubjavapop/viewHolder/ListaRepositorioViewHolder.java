package br.com.githubjavapop.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.githubjavapop.R;

public class ListaRepositorioViewHolder extends RecyclerView.ViewHolder {

    public TextView nomeRepositorio;
    public TextView descricao;
    public TextView forks;
    public TextView stars;
    public TextView username;
    public ImageView foto;

    public ListaRepositorioViewHolder(@NonNull View itemView) {
        super(itemView);

        nomeRepositorio = (TextView) itemView.findViewById(R.id.textoNomeRepo);
        descricao = (TextView) itemView.findViewById(R.id.textoDescrRepo);
        forks = (TextView) itemView.findViewById(R.id.textoForks);
        stars = (TextView) itemView.findViewById(R.id.textoStarts);
        username = (TextView) itemView.findViewById(R.id.textoUsernameRepo);
        foto = (ImageView) itemView.findViewById(R.id.imagemUsuarioRepo);
    }
}
