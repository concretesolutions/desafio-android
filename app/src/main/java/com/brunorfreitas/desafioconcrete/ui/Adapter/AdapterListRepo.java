package com.brunorfreitas.desafioconcrete.ui.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brunorfreitas.desafioconcrete.R;
import com.brunorfreitas.desafioconcrete.data.Model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterListRepo extends RecyclerView.Adapter<AdapterListRepo.MyViewHolder> {

    private Context context;
    private List<Item> itemList;

    public AdapterListRepo(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    public void addItens(List<Item> repoList) {
        this.itemList = repoList;
        notifyDataSetChanged();
    }

    public void addMoreItems(List<Item> items) {
        this.itemList.addAll(items);
        notifyDataSetChanged();
    }

    public interface I_AdapterListRepo {
        void onClickRepo(int position);
    }

    private I_AdapterListRepo i_adapterListRepo;

    public void setI_adapterListRepo(I_AdapterListRepo i_adapterListRepo) {
        this.i_adapterListRepo = i_adapterListRepo;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_repo, parent, false);
        return new MyViewHolder(v, context);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.bind(itemList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView tv_nomeRepositorio;
        TextView tv_descricaoRepositorio;
        TextView tv_nmrFork;
        TextView tv_nmrstar;
        TextView tv_username;
        TextView tv_NomeSobrenome;
        ImageView iv_user;

        public MyViewHolder(@NonNull View itemView, Context context) {
            super(itemView);


            cardView = itemView.findViewById(R.id.item_repo_cv);
            tv_nomeRepositorio = itemView.findViewById(R.id.item_repo_tv_nome_repo);
            tv_descricaoRepositorio = itemView.findViewById(R.id.item_repo_tv_desc_repo);
            tv_nmrFork = itemView.findViewById(R.id.item_repo_tv_nmr_fork);
            tv_nmrstar = itemView.findViewById(R.id.item_repo_tv_nmr_stars);
            tv_username = itemView.findViewById(R.id.item_repo_tv_username);
            tv_NomeSobrenome = itemView.findViewById(R.id.item_repo_tv_nome_sobrenome);
            iv_user = itemView.findViewById(R.id.item_repo_iv_user);
        }

        public void bind(Item item, final int position) {

            String nomeRepo = item.getName();
            String descricaoRepo = item.getDescription();
            String nmrFork = String.valueOf(item.getForksCount());
            String nmrStar = String.valueOf(item.getStargazersCount());
            String username = item.getOwner().getLogin();
            String fullName = item.getFullName();
            String imagemUser = item.getOwner().getAvatarUrl();

            tv_nomeRepositorio.setText(nomeRepo);
            tv_descricaoRepositorio.setText(descricaoRepo);
            tv_nmrFork.setText(nmrFork);
            tv_nmrstar.setText(nmrStar);
            tv_username.setText(username);
            tv_NomeSobrenome.setText(fullName);
            Picasso.get().load(imagemUser).into(iv_user);


            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (i_adapterListRepo != null) {
                        i_adapterListRepo.onClickRepo(position);
                    }
                }
            });
        }
    }
}
