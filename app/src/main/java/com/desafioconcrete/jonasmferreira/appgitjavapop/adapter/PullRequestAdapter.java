package com.desafioconcrete.jonasmferreira.appgitjavapop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.desafioconcrete.jonasmferreira.appgitjavapop.R;
import com.desafioconcrete.jonasmferreira.appgitjavapop.domain.PullRequest;
import com.desafioconcrete.jonasmferreira.appgitjavapop.utils.Tools;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.ViewHolder>{
    List<PullRequest> pullrequestList = new ArrayList<>();

    public PullRequestAdapter(List<PullRequest> pullrequestList) {
        this.pullrequestList = pullrequestList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View pullrequestView = inflater.inflate(R.layout.item_pull_request, parent, false);

        ViewHolder viewHolder = new ViewHolder(pullrequestView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ImageLoader imageLoader = ImageLoader.getInstance();

        PullRequest pullrequest = pullrequestList.get(position);

        TextView tvPullRequestTitle = holder.tvPullRequestTitle;
        tvPullRequestTitle.setText(pullrequest.getTitle());

        TextView tvPullRequestDescr = holder.tvPullRequestDescr;
        tvPullRequestDescr.setText(pullrequest.getBody());

        TextView tvPullRequestUsername = holder.tvPullRequestUsername;
        tvPullRequestUsername.setText(pullrequest.getUser().getLogin());

        TextView tvPullRequestFullname = holder.tvPullRequestFullname;
        tvPullRequestFullname.setText(pullrequest.getUser().getLogin());

        TextView tvPullRequestDate = holder.tvPullRequestDate;
        String dt = pullrequest.getCreatedAt();
        dt = dt.replace("T"," ");
        dt = dt.replace("Z","s");
        tvPullRequestDate.setText(Tools.dateDB2BR(dt.trim()));

        ImageView civPullRequestUser = holder.civPullRequestUser;

        imageLoader.displayImage(pullrequest.getUser().getAvatarUrl(), civPullRequestUser);

    }

    @Override
    public int getItemCount() {
        return pullrequestList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvPullRequestTitle,tvPullRequestDescr,tvPullRequestUsername,tvPullRequestFullname,tvPullRequestDate;
        public ImageView civPullRequestUser;
        public ViewHolder(View itemView) {
            super(itemView);
            tvPullRequestTitle = (TextView) itemView.findViewById(R.id.tvPullRequestTitle);
            tvPullRequestDescr = (TextView) itemView.findViewById(R.id.tvPullRequestDescr);
            tvPullRequestUsername = (TextView) itemView.findViewById(R.id.tvPullRequestUsername);
            tvPullRequestFullname = (TextView) itemView.findViewById(R.id.tvPullRequestFullname);
            civPullRequestUser = (ImageView)itemView.findViewById(R.id.civPullRequestUser);
            tvPullRequestDate = (TextView) itemView.findViewById(R.id.tvPullRequestDate);
        }
    }
}