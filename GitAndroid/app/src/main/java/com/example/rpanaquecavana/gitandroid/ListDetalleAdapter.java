package com.example.rpanaquecavana.gitandroid;

import android.app.AlertDialog;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rpanaquecavana.gitandroid.DetalleModelo.Detail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListDetalleAdapter extends RecyclerView.Adapter<ListDetalleAdapter.ViewHolderDetalle> {

    private ArrayList<Detail> repodata ;

    public ListDetalleAdapter(ArrayList<Detail> repodata) {
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
    public void onBindViewHolder(@NonNull ViewHolderDetalle holder,final int position) {

        if(repodata.size()!=0)
        {
            if(repodata.get(position).getHead().getRepo().getName()!=null)
            {

                holder.txtitulo.setText(repodata.get(position).getHead().getRepo().getName());
                holder.txdescripcion.setText(repodata.get(position).getHead().getRepo().getDescription());
                holder.txautor.setText(repodata.get(position).getHead().getRepo().getOwner().getLogin());
                Picasso.get().load(repodata.get(position).getHead().getRepo().getOwner().getAvatarUrl()).into(holder.iconoautor);
            }
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
                builder.setMessage(R.string.servidor)
                        .setTitle(R.string.aviso);
                AlertDialog alert = builder.create();
            }
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
            builder.setMessage(R.string.servidor)
                    .setTitle(R.string.aviso);
            AlertDialog alert = builder.create();
        }

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
            iconoautor = (ImageView) view.findViewById(R.id.iconuser);
        }
    }
}
