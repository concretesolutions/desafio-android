package com.marcos.desafioandroidconcrete.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.marcos.desafioandroidconcrete.R;
import com.marcos.desafioandroidconcrete.domain.RepositoryDetail;
import com.marcos.desafioandroidconcrete.util.ItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marco on 16,Abril,2020
 */
public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.MyViewHolder> {


    private List<RepositoryDetail> list = new ArrayList<>();
    private static ItemClickListener itemClickListener;

    public RepositoryAdapter(List<RepositoryDetail> list) {
        this.list = list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_repository, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final RepositoryDetail repositoryDetail = list.get(position);
        if (repositoryDetail != null) {
            holder.nameRepository.setText(repositoryDetail.getName());
            holder.descriptionRepository.setText(repositoryDetail.getDescription());
            holder.numberFork.setText(String.valueOf(repositoryDetail.getForks()));
            holder.numberStar.setText(String.valueOf(repositoryDetail.getStargazers_count()));
            holder.username.setText(repositoryDetail.getOwner().getLogin());
            Picasso.get().load(repositoryDetail.getOwner().getAvatarUrl()
            ).noFade().fit().error(R.drawable.ic_avatar).into(holder.image);
        }
    }


    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void update(List<RepositoryDetail> listItems) {
        this.list = listItems;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameRepository, descriptionRepository, numberFork, numberStar, username;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            nameRepository = view.findViewById(R.id.tv_name_repository);
            descriptionRepository = view.findViewById(R.id.tv_description_repository);
            numberFork = view.findViewById(R.id.tv_number_fork);
            numberStar = view.findViewById(R.id.tv_number_star);
            username = view.findViewById(R.id.tv_username);
            image = view.findViewById(R.id.iv_avatar);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if (itemClickListener != null) {
                itemClickListener.onItemClick(getAdapterPosition());
            }

        }
    }
}