package com.alexandreobs.testeconcrete.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alexandreobs.testeconcrete.R;
import com.alexandreobs.testeconcrete.model.pojo.pull.Request;
import com.alexandreobs.testeconcrete.view.interfaces.OnClickRequest;
import com.squareup.picasso.Picasso;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {

    private List<Request> requestList;
    private OnClickRequest listener;

    public RequestAdapter(List<Request> requestList, OnClickRequest listener) {
        this.requestList = requestList;
      this.listener = listener;
    }


    @NonNull
    @Override
    public RequestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_pulls, parent, false);
        return new RequestAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestAdapter.ViewHolder holder, int position) {
        final Request request = requestList.get(position);
        holder.onBind(request);

        holder.itemView.setOnClickListener(v -> listener.OnClick(request));
    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }

    public void atualizaLista(List<Request> novaLista) {
        if (this.requestList.isEmpty()) {
            this.requestList = novaLista;
        } else {
            this.requestList.addAll(novaLista);
        }
        notifyDataSetChanged();

    }


     class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView userIcon;
        private TextView titulo;
        private TextView desc;
        private TextView username;
        private TextView data;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.tituloPullRes);
            desc = itemView.findViewById(R.id.DescricaoPullRe);
            username = itemView.findViewById(R.id.NomeUserPullRe);
            userIcon = itemView.findViewById(R.id.fotoPerfilCardPull);
            data = itemView.findViewById(R.id.DataPull);

        }

        public void onBind(Request request) {

            titulo.setText(request.getTitle());
            desc.setText(request.getBody());
            username.setText(request.getUser().getLogin());

            data.setText("creation date " +request.getCreatedAt());

            Picasso.get().load(request.getUser().getAvatarUrl()).into(userIcon);


        }
    }
}






















