package com.example.rpanaquecavana.gitandroid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rpanaquecavana.gitandroid.Modelos.Item;
import com.example.rpanaquecavana.gitandroid.Modelos.RepoGit;

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

    }

    @Override
    public int getItemCount() {
        return repodata.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder
    {
        TextView txttitulo, txtdescripcion, txtuser;
        ImageView imagen;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            txttitulo = itemView.findViewById(R.id.txttitle);
            txtdescripcion = itemView.findViewById(R.id.txtdescription);
            txtuser = itemView.findViewById(R.id.txtusername);
            imagen = itemView.findViewById(R.id.icono);
        }

        public void asignardatos(String repoGit)
        {
            txtuser.setText(repoGit);
        }
    }
}
