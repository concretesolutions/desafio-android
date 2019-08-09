package com.postulacion.githubjavapop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.postulacion.githubjavapop.PullRequestActivity;
import com.postulacion.githubjavapop.R;
import com.postulacion.githubjavapop.model.User;
import com.postulacion.githubjavapop.model.Repository;
import com.squareup.picasso.Picasso;
import java.util.List;

public class RepositoryRecyclerViewAdapter extends RecyclerView.Adapter<RepositoryRecyclerViewAdapter.ViewHolder> {

    private List<Repository> repositoryList;
    private Context context;

    public RepositoryRecyclerViewAdapter(List<Repository> repositoryList, Context context) {
        this.repositoryList = repositoryList;
        this.context = context;
    }

    @NonNull
    @Override
    public RepositoryRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repository, parent, false);
        return new RepositoryRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryRecyclerViewAdapter.ViewHolder viewHolder, int position) {
        Repository repository = repositoryList.get(position);
        User user = repository.getUser();

        viewHolder.repositoryTitle.setText(repository.getName());
        viewHolder.repositoryDescription.setText(repository.getDescription());
        viewHolder.repositoryForks.setText(repository.getForks());
        viewHolder.repositoryStars.setText(repository.getStargazers_count());
        viewHolder.repositoryUserName.setText(user.getUserName());
        viewHolder.repositoryUserNickName.setText(user.getNickName());
        Picasso.with(context).load(repositoryList.get(position).getUser().getAvatarUrl()).into(viewHolder.repositoryUserImage);
    }

    @Override
    public int getItemCount() {
        return repositoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView repositoryTitle;
        private TextView repositoryDescription;
        private TextView repositoryForks;
        private TextView repositoryStars;
        private TextView repositoryUserName;
        private TextView repositoryUserNickName;
        private ImageView repositoryUserImage;

        public ViewHolder(View view) {
            super(view);
            repositoryTitle = view.findViewById(R.id.repository_title);
            repositoryDescription = view.findViewById(R.id.repository_description);
            repositoryForks = view.findViewById(R.id.repository_forks);
            repositoryStars = view.findViewById(R.id.repository_stars);
            repositoryUserName = view.findViewById(R.id.repository_user_name);
            repositoryUserNickName = view.findViewById(R.id.repository_user_nickname);
            repositoryUserImage = view.findViewById(R.id.repository_user_image);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Repository repository = repositoryList.get(getAdapterPosition());
            User user = repository.getUser();

            String urlPulls = "https://api.github.com/repos/" + user.getUserName() + "/" + repository.getName() + "/pulls";

            Intent intent = new Intent(context.getApplicationContext(), PullRequestActivity.class);
            intent.putExtra("pullRequestURL", urlPulls);
            context.startActivity(intent);
        }
    }
}
