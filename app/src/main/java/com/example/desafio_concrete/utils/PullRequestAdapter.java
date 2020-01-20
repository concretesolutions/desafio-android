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
import com.example.desafio_concrete.networkUtils.PullRequest;

import java.util.ArrayList;
import java.util.List;

public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.PullRequestViewHolder> {

    private final Context mContext;
    private List<PullRequest> pullRequests;


    public PullRequestAdapter(Context mContext) {
        this.mContext = mContext;
        this.pullRequests = new ArrayList<>();
    }

    @NonNull
    @Override
    public PullRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).
                inflate(R.layout.pull_list_item, parent, false);

        return new PullRequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PullRequestViewHolder holder, int position) {

        String title = pullRequests.get(position).getTitle();
        if(title==null || title.equals("")){
            holder.title.setText("Not informed");
        }else{
            holder.title.setText(title);
        }

        String body = pullRequests.get(position).getBody();
        if(body==null || body.equals("")){
            holder.body.setText("Body not informed");
        }else{
            holder.body.setText(body);
        }

        String userName = pullRequests.get(position).getOwner().getNameAuthor();
        if(userName == null || userName.equals("")){
            holder.userName.setText("Use name not informed");
        }else{
            holder.userName.setText(userName);
        }

        String avatarUrl = pullRequests.get(position).getOwner().getAvatarUrl();
        Glide.with(mContext).load(avatarUrl).apply(RequestOptions.circleCropTransform())
                .into(holder.avatarUser);


    }

    @Override
    public int getItemCount() {
        return pullRequests.size();
    }



    public void add(PullRequest data){
        pullRequests.add(data);
        notifyItemInserted(pullRequests.size() -1);
    }

    public void addPullRequests(List<PullRequest> data){
        for(PullRequest item : data){
            add(item);
        }
    }


    public class PullRequestViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView body;
        TextView userName;
        ImageView avatarUser;


        public PullRequestViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.pull_title);
            body = (TextView) itemView.findViewById(R.id.pull_body);
            userName = (TextView) itemView.findViewById(R.id.user_name_pull);
            avatarUser = (ImageView) itemView.findViewById(R.id.avatar_user_pull);
        }
    }
}
