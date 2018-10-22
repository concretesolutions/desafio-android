package com.challenge.accenture.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.challenge.accenture.R;
import com.challenge.accenture.activities.Main;
import com.challenge.accenture.objects.Item;
import com.challenge.accenture.objects.Repository;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;


/*
 * Created by Cezhar Arevalo on 10/21/18.
 */
public class MainAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Item> leRepos;
    private int leResource;
    private ImageLoader leImageLoader;
    private Main leMain;

    public MainAdapter(Context context, List<Item> items, int resource, ImageLoader imageLoader, Main main){

        this.context = context;
        this.leRepos = items;
        this.leResource = resource;
        this.leImageLoader = imageLoader;
        this.leMain = main;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context)
                .inflate(leResource, parent, false);
        return new leHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Item leRepo = leRepos.get(position);

        ((leHolder) holder).bind(leRepo);
    }

    @Override
    public int getItemCount() {
        return leRepos.size();
    }

    private class leHolder extends RecyclerView.ViewHolder {


        TextView name, desc, username, forks, stars;
        ImageView img;

        leHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.repo_name);
            desc = itemView.findViewById(R.id.repo_desc);
            username = itemView.findViewById(R.id.repo_user);
            img = itemView.findViewById(R.id.repo_user_img);
            forks = itemView.findViewById(R.id.forks);
            stars = itemView.findViewById(R.id.stars);
        }

        void bind(final Item leRepo) {

            name.setText(leRepo.getName());
            desc.setText(leRepo.getDescription());
            username.setText(leRepo.getOwner().getLogin());
            leImageLoader.displayImage(leRepo.getOwner().getAvatarUrl(), img);

            String leForks = "Forks: "+leRepo.getForks();
            String leStars = "Stars: "+leRepo.getStargazersCount();

            forks.setText(leForks);
            stars.setText(leStars);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    leMain.openPulls(leRepo);
                }
            });
        }
    }
}
