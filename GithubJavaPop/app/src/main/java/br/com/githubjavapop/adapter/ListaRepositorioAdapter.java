package br.com.githubjavapop.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import br.com.githubjavapop.R;
import br.com.githubjavapop.entidade.ListaRepositorio;
import br.com.githubjavapop.entidade.Repositorio;
import br.com.githubjavapop.viewHolder.ListaRepositorioViewHolder;

public class ListaRepositorioAdapter extends RecyclerView.Adapter<ListaRepositorioViewHolder> implements
        View.OnClickListener{

    private ListaRepositorio listaRepositorio;

    private View.OnClickListener clickListener;

    public ListaRepositorioAdapter(ListaRepositorio listaRepositorio) {
        this.listaRepositorio = listaRepositorio;
    }

    @NonNull
    @Override
    public ListaRepositorioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.layout_repositorio, viewGroup, false);
        view.setOnClickListener(this);

        return new ListaRepositorioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaRepositorioViewHolder listaRepositorioViewHolder, int i) {

        if(listaRepositorio != null && listaRepositorio.getListaRepositorio().size() > 0) {
            Repositorio repo = listaRepositorio.getListaRepositorio().get(i);
            listaRepositorioViewHolder.nomeRepositorio.setText(repo.getNomeRepositorio());
            listaRepositorioViewHolder.descricao.setText(repo.getDescricao());
            listaRepositorioViewHolder.forks.setText(String.valueOf(repo.getForks()));
            listaRepositorioViewHolder.stars.setText(String.valueOf(repo.getStars()));
            listaRepositorioViewHolder.username.setText(repo.getUsuario().getUsername());
            Picasso.get().load(repo.getUsuario().getFoto()).into(listaRepositorioViewHolder.foto);
        }

    }

    @Override
    public int getItemCount() {
        return listaRepositorio.getListaRepositorio().size();
    }

    public void setOnClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public void onClick(View v) {
        if(clickListener != null) clickListener.onClick(v);
    }
}
