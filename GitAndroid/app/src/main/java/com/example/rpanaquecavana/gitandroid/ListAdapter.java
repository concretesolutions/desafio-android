package com.example.rpanaquecavana.gitandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.rpanaquecavana.gitandroid.Modelos.Item;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolderDatos> implements View.OnClickListener
{
    private ArrayList<Item> repodata;
    private View.OnClickListener listener;

    public ListAdapter(ArrayList<Item> repodata) {
        this.repodata = repodata;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_git,parent,false);
        ViewHolderDatos viewHolderDatos = new ViewHolderDatos(view);
        view.setOnClickListener(this);
        return viewHolderDatos;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderDatos holder, final int position) {
        holder.txtuser.setText(repodata.get(position).getOwner().getLogin());
        holder.txtdescripcion.setText(repodata.get(position).getDescription());
        holder.txttitulo.setText(repodata.get(position).getName());
        holder.txtforks.setText(String.valueOf(repodata.get(position).getForks()));
        holder.txtraiting.setText(String.valueOf(repodata.get(position).getWatchers()));
        Picasso.get().load(repodata.get(position).getOwner().getAvatarUrl()).into(holder.imagen);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetalleActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra("autor",repodata.get(position).getOwner().getLogin());
                intent.putExtra("repo",repodata.get(position).getName());
                intent.putExtras(bundle);
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return repodata.size();
    }

    public void  setOnClickListener(View.OnClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null)
        {
            listener.onClick(v);
        }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder
    {
        TextView txttitulo, txtdescripcion, txtuser, txtraiting,txtforks;
        ImageView imagen;
        RelativeLayout relativeLayout;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            txttitulo = (TextView) itemView.findViewById(R.id.txttitle);
            txtdescripcion = (TextView) itemView.findViewById(R.id.txtdescription);
            txtuser = (TextView) itemView.findViewById(R.id.txtusername);
            imagen = (ImageView) itemView.findViewById(R.id.icono);
            txtforks = (TextView) itemView.findViewById(R.id.txtforks);
            txtraiting = (TextView) itemView.findViewById(R.id.txtstar);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.recyclervista);

        }

    }
}
