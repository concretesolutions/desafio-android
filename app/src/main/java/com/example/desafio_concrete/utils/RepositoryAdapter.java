package com.example.desafio_concrete.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.desafio_concrete.R;
import com.example.desafio_concrete.networkUtils.Item;

import java.util.ArrayList;
import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.
        RepositoryAdapterViewHolder>{


    private final Context mContext;
    private List<Item> repositories;
    private RepositoryAdapterOnClickHandler mClickHandler;



    public interface RepositoryAdapterOnClickHandler {
        void onClick(int data);
    }


    public RepositoryAdapter(Context context, RepositoryAdapterOnClickHandler onClickHandler) {
        this.mContext = context;
        this.repositories = new ArrayList<>();
        this.mClickHandler = onClickHandler;
    }




    @Override
    public RepositoryAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).
                inflate(R.layout.repository_list_item, parent, false);

        return new RepositoryAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryAdapterViewHolder holder, int position) {


        String name = repositories.get(position).getName();
        if(name == null || name.equals("")){
            holder.repositoryName.setText("Name not informed");
        }else{
            holder.repositoryName.setText(name);
        }

        String description = repositories.get(position).getDescription();
        if(description == null || description.equals("")){
            holder.repositoryDescription.setText("Description not informed");
        }else{
            holder.repositoryDescription.setText(description);
        }


        String forkCount = repositories.get(position).getNumberForks();
        if(forkCount == null || forkCount.equals("")){
            holder.forkCount.setText("0");
        }else{
            holder.forkCount.setText(forkCount);
        }


        String starCount = repositories.get(position).getNumberStars();
        if(starCount == null || starCount.equals("")){
            holder.starCount.setText("0");
        }else{
            holder.starCount.setText(starCount);
        }


        String fullName = repositories.get(position).getFullName();
        if(fullName == null || fullName.equals("")){
            holder.userFullName.setText("Not informed");
        }else{
            holder.userFullName.setText(fullName);
        }



        String userName = repositories.get(position).getOwner().getNameAuthor();
        if(userName == null || userName.equals("")){
            holder.userName.setText("Not informed");
        }else{
            holder.userName.setText(userName);
        }

        String avatarUrl = repositories.get(position).getOwner().getAvatarUrl();
        Glide.with(mContext).load(avatarUrl).
                apply(RequestOptions.circleCropTransform()).into(holder.userAvatar);

        holder.forkIcon.setImageResource(R.drawable.branch);
        holder.starIcon.setImageResource(R.drawable.star);

    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }


    public void add(Item data){
        repositories.add(data);
        notifyItemInserted(repositories.size() -1);
    }

    public void addRepositories(List<Item> data){
        for(Item item : data){
            add(item);
        }
    }


    public Item getItem(int position){
        return  repositories.get(position);
    }

    public List<Item> getData(){
        return repositories;
    }


    class RepositoryAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView repositoryName;
        TextView repositoryDescription;
        TextView forkCount;
        ImageView forkIcon;
        TextView starCount;
        ImageView starIcon;
        TextView userName;
        TextView userFullName;
        ImageView userAvatar;

        public RepositoryAdapterViewHolder(View itemView) {
            super(itemView);

            repositoryName = (TextView) itemView.findViewById(R.id.repository_name);
            repositoryDescription = (TextView) itemView.findViewById(R.id.repository_description);
            forkCount = (TextView) itemView.findViewById(R.id.fork_count);
            forkIcon = (ImageView) itemView.findViewById(R.id.fork);
            starCount = (TextView) itemView.findViewById(R.id.star_count);
            starIcon = (ImageView) itemView.findViewById(R.id.star);
            userName = (TextView) itemView.findViewById(R.id.user_name);
            userFullName = (TextView) itemView.findViewById(R.id.full_name);
            userAvatar = (ImageView) itemView.findViewById(R.id.avatar_user);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mClickHandler.onClick(adapterPosition);

        }

    }
}
