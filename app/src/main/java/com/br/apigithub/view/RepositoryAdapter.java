package com.br.apigithub.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.br.apigithub.R;
import com.br.apigithub.beans.GithubRepository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryVH> {
    private List<GithubRepository> repositories;

    @Override
    public RepositoryVH onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_repository, parent, false);
        return new RepositoryVH(view);
    }

    @Override
    public void onBindViewHolder(RepositoryVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setRepositories(List<GithubRepository> repositories) {
        if (this.repositories != null) {
            this.repositories.addAll(repositories);
        } else {
            this.repositories = repositories;
        }
        notifyDataSetChanged();
    }
}

class RepositoryVH extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.tv_repo_name)
    TextView tvRepoName;

    @BindView(R.id.tv_repo_desc)
    TextView tvRepoDesc;

    @BindView(R.id.tv_username)
    TextView tvUsername;

    @BindView(R.id.tv_fullname)
    TextView tvFullname;

    @BindView(R.id.circleImageView)
    CircleImageView ivUser;

    @BindView(R.id.tv_quant_fork)
    TextView tvFork;

    @BindView(R.id.tv_quant_star)
    TextView tvStar;

    public RepositoryVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
    }
}
