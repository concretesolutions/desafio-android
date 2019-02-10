package com.example.rpanaquecavana.gitandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rpanaquecavana.gitandroid.Modelos.Item;
import com.example.rpanaquecavana.gitandroid.Modelos.RepoGit;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolderDatos>
{
    private Context context;
    private ArrayList<Item> repodata;

    public ListAdapter(ArrayList<Item> repodata) {
        this.repodata = repodata;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detalle,parent,false);
        ViewHolderDatos viewHolderDatos = new ViewHolderDatos(view);
        return viewHolderDatos;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
       // RepoGit repoGit = repodata.get(position);
        holder.txtuser.setText(repodata.get(position).getOwner().getLogin());
        holder.txtdescripcion.setText(repodata.get(position).getDescription());
        holder.txttitulo.setText(repodata.get(position).getName());
        holder.txtforks.setText(String.valueOf(repodata.get(position).getForks()));
        holder.txtraiting.setText(String.valueOf(repodata.get(position).getWatchers()));
        Picasso.get().load(repodata.get(position).getOwner().getAvatarUrl()).into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return repodata.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder
    {
        TextView txttitulo, txtdescripcion, txtuser, txtraiting,txtforks;
        ImageView imagen;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            txttitulo = (TextView) itemView.findViewById(R.id.txttitle);
            txtdescripcion = (TextView) itemView.findViewById(R.id.txtdescription);
            txtuser = (TextView) itemView.findViewById(R.id.txtusername);
            imagen = (ImageView) itemView.findViewById(R.id.icono);
            txtforks = (TextView) itemView.findViewById(R.id.txtforks);
            txtraiting = (TextView) itemView.findViewById(R.id.txtstar);
        }

        public void asignardatos(String repoGit)
        {
            txtuser.setText(repoGit);
        }
    }
}
