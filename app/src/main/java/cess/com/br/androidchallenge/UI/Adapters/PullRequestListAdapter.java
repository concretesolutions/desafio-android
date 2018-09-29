package cess.com.br.androidchallenge.UI.Adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cess.com.br.androidchallenge.Model.Remote.PR;
import cess.com.br.androidchallenge.Model.Remote.Repo;
import cess.com.br.androidchallenge.R;
import cess.com.br.androidchallenge.UI.Activies.PullRequestListActivity;

public class PullRequestListAdapter extends RecyclerView.Adapter<PullRequestListAdapter.RepoViewHolder> {

    private List<PR> prList;
    private Context context;

    public PullRequestListAdapter(List<PR> prs, Context context) {
        this.prList = prs;
        this.context = context;
    }


    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_pullrequest, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        holder.mPrName.setText(prList.get(position).getTitle());
        holder.mPrDescription.setText(prList.get(position).getBody());
        holder.mPrOwnerLogin.setText(prList.get(position).getUser().getLogin());


        String dtStart = prList.get(position).getCreatedAt();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy' 'HH:mm");
        try {
            Date date = format.parse(dtStart);
            System.out.println(date);
            holder.mPrOwnerFullname.setText(String.valueOf(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }



        Glide.with(context)
                .load(prList.get(position).getUser().getAvatarUrl())
                .into(holder.mPrOwnerAvatar);


    }

    @Override
    public int getItemCount() {
        return prList.size();
    }

    class RepoViewHolder extends RecyclerView.ViewHolder{

        TextView mPrName;
        TextView mPrDescription;
        TextView mPrOwnerLogin;
        TextView mPrOwnerFullname;

        ImageView mPrOwnerAvatar;

        RepoViewHolder(View itemView) {
            super(itemView);

            mPrName = itemView.findViewById(R.id.tv_pr_name);
            mPrDescription = itemView.findViewById(R.id.tv_pr_description);
            mPrOwnerLogin = itemView.findViewById(R.id.tv_pr_owner_login);
            mPrOwnerFullname = itemView.findViewById(R.id.tv_pr_owner_fullname);

            mPrOwnerAvatar = itemView.findViewById(R.id.iv_pr_user_avatar);

        }
    }
}
