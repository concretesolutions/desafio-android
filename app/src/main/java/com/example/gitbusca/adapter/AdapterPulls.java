package com.example.gitbusca.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gitbusca.R;
import com.example.gitbusca.activity.PullActivity;
import com.example.gitbusca.helper.CircleTransform;
import com.example.gitbusca.model.Pull;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterPulls extends RecyclerView.Adapter<AdapterPulls.MyViewHolder> {

    private List<Pull> listPulls;
    private PullActivity activity;

    public AdapterPulls(List<Pull> listPulls, PullActivity activity) {
        this.listPulls = listPulls;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_lista_pull, parent, false);

        return new MyViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pull pull = listPulls.get( position );

        holder.title.setText( pull.getTitle() );
        holder.descPull.setText( String.valueOf(pull.getBody()) );
        holder.usuario.setText( pull.getUser().getLogin() );

        Picasso.with( activity )
                .load( pull.getUser().getAvatar_url() )
                .transform( new CircleTransform() )
                .into( holder.imageUser );

    }

    @Override
    public int getItemCount() {
        return listPulls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView descPull;
        TextView usuario;
        TextView nome;
        ImageView imageUser;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textTitulo);
            descPull = itemView.findViewById(R.id.textDescPull);
            usuario = itemView.findViewById(R.id.textNomeUsuario);
            nome = itemView.findViewById(R.id.textNomeCompleto);
            imageUser = itemView.findViewById(R.id.imageUser);
        }
    }
}
