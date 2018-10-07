package com.br.apigithub.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.br.apigithub.R;
import com.br.apigithub.beans.GithubRepository;
import com.br.apigithub.beans.Item;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryVH> {
    private GithubRepository repository;
    private Context mContext;

    public RepositoryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RepositoryVH onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_repository, parent, false);
        return new RepositoryVH(view);
    }

    @Override
    public void onBindViewHolder(RepositoryVH holder, int position) {
        Item item = repository.getItems().get(position);
        holder.tvRepoName.setText(item.getName());
        holder.tvUsername.setText(item.getOwner().getLogin());
        holder.tvRepoDesc.setText(item.getDescription());
        holder.tvStar.setText(String.valueOf(item.getStarsCount()));
        holder.tvFork.setText(String.valueOf(item.getForksCount()));
        if (item.getOwner().getAvatarUrl() != null && !item.getOwner().getAvatarUrl().isEmpty()) {
            Glide.with(mContext).load(item.getOwner().getAvatarUrl()).into(holder.ivUser);
        }
    }

    @Override
    public int getItemCount() {
        return repository != null && repository.getItems() != null
                && !repository.getItems().isEmpty() ? repository.getItems().size() : 0;
    }

    public void setRepository(GithubRepository repository) {
        if (this.repository != null) {
            this.repository.getItems().addAll(repository.getItems());
        } else {
            this.repository = repository;
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

    @BindView(R.id.circleImageView)
    CircleImageView ivUser;

    @BindView(R.id.tv_fork)
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
