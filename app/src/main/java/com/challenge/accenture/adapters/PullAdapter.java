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
import com.challenge.accenture.activities.PullActivity;
import com.challenge.accenture.objects.Item;
import com.challenge.accenture.objects.Pull;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;


/*
 * Created by Cezhar Arevalo on 10/21/18.
 */
public class PullAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Pull> lePulls;
    private int leResource;
    private ImageLoader leImageLoader;
    private PullActivity lePullActivity;

    public PullAdapter(Context context, List<Pull> items, int resource, ImageLoader imageLoader, PullActivity pullActivity){

        this.context = context;
        this.lePulls = items;
        this.leResource = resource;
        this.leImageLoader = imageLoader;
        this.lePullActivity = pullActivity;

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
        Pull lePull = lePulls.get(position);

        ((leHolder) holder).bind(lePull);
    }

    @Override
    public int getItemCount() {
        return lePulls.size();
    }

    private class leHolder extends RecyclerView.ViewHolder {


        TextView title, desc, user;
        ImageView img;

        leHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.pull_title);
            desc = itemView.findViewById(R.id.pull_body);
            user = itemView.findViewById(R.id.pull_user);
            img = itemView.findViewById(R.id.pull_user_img);
        }

        void bind(final Pull lePull) {

            title.setText(lePull.getTitle());
            desc.setText(lePull.getBody());
            user.setText(lePull.getUser().getLogin());
            leImageLoader.displayImage(lePull.getUser().getAvatarUrl(), img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lePullActivity.goToBrowser(lePull);
                }
            });
        }
    }
}
