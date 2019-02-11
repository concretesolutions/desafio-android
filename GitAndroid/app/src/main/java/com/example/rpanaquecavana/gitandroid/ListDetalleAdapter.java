package com.example.rpanaquecavana.gitandroid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rpanaquecavana.gitandroid.DetalleModelo.Detalle;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListDetalleAdapter extends RecyclerView.Adapter<ListDetalleAdapter.ViewHolderDetalle> {

    private ArrayList<Detalle> repodata;

    public ListDetalleAdapter(ArrayList<Detalle> repodata) {
        this.repodata = repodata;
    }

    @NonNull
    @Override
    public ViewHolderDetalle onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detalle_lista,parent,false);
        ViewHolderDetalle viewHolderDetalle = new ViewHolderDetalle(view);
        return viewHolderDetalle;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDetalle holder, int position) {

        holder.txautor.setText(repodata.get(position).getHead().getRepo().getOwner().getLogin());
        holder.txtitulo.setText(repodata.get(position).getHead().getRepo().getName());
        holder.txdescripcion.setText(repodata.get(position).getHead().getRepo().getDescription());
        Picasso.get().load(repodata.get(position).getHead().getRepo().getOwner().getAvatarUrl()).into(holder.iconoautor);
    }

    @Override
    public int getItemCount() {
        return repodata.size();
    }

    public class ViewHolderDetalle extends RecyclerView.ViewHolder
    {
        TextView txtitulo, txdescripcion,txautor;
        ImageView iconoautor;

        public ViewHolderDetalle(View view) {
            super(view);

            txautor = (TextView) view.findViewById(R.id.txtdetail_username);
            txdescripcion = (TextView) view.findViewById(R.id.txtdetail_desc);
            txtitulo = (TextView) view.findViewById(R.id.txtdetail_title);
        }
    }
}
