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

import java.util.List;

import cess.com.br.androidchallenge.Model.Remote.Repo;
import cess.com.br.androidchallenge.R;
import cess.com.br.androidchallenge.UI.Activies.PullRequestListActivity;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.RepoViewHolder> {

    private List<Repo> repoList;
    private Context context;

    public RepoListAdapter(List<Repo> repos, Context context) {
        this.repoList = repos;
        this.context = context;
    }


    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_repo, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        holder.mRepoName.setText(repoList.get(position).getName());
        holder.mRepoDescription.setText(repoList.get(position).getDescription());
        holder.mRepoBranches.setText(String.valueOf(repoList.get(position).getForksCount()));
        holder.mRepoStars.setText(String.valueOf(repoList.get(position).getStargazersCount()));
        holder.mUsername.setText(repoList.get(position).getOwner().getLogin());
        //holder.mUserFullname.setText(repoList.get(position).getOwner().get);

        Glide.with(context)
                .load(repoList.get(position).getOwner().getAvatarUrl())
                .into(holder.mUserAvatar);

        holder.onClick(holder.itemView);

    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    class RepoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView mRepoName;
        TextView mRepoDescription;
        TextView mRepoBranches;
        TextView mRepoStars;
        TextView mUsername;
        TextView mUserFullname;

        ImageView mUserAvatar;

        RepoViewHolder(View itemView) {
            super(itemView);

            mRepoName = itemView.findViewById(R.id.tv_repo_name);
            mRepoDescription = itemView.findViewById(R.id.tv_repo_description);
            mRepoBranches = itemView.findViewById(R.id.tv_repo_branches);
            mRepoStars = itemView.findViewById(R.id.tv_repo_stars);
            mUsername = itemView.findViewById(R.id.tv_username);
            mUserFullname = itemView.findViewById(R.id.tv_user_fullname);
            mUserAvatar = itemView.findViewById(R.id.iv_user_fullname);

        }


        @Override
        public void onClick(View v) {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent prsIntent = new Intent(context, PullRequestListActivity.class);

                    Repo repo = getItem(getAdapterPosition());

                    prsIntent.putExtra("USER_NAME", repo.getOwner().getLogin());
                    prsIntent.putExtra("REPO_NAME", repo.getName());

                    context.startActivity(prsIntent);
                }
            });
        }
    }

    Repo getItem(int id) {
        return repoList.get(id);
    }

    public Boolean addRepo(List<Repo> repos) {
        repoList.addAll(repos);
        notifyDataSetChanged();

        return true;
    }
}
