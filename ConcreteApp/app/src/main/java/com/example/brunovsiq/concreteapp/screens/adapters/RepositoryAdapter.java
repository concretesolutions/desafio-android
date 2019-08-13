package com.example.brunovsiq.concreteapp.screens.adapters;

import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.brunovsiq.concreteapp.R;
import com.example.brunovsiq.concreteapp.models.Repository;
import com.squareup.picasso.Picasso;

public class RepositoryAdapter extends PagedListAdapter<Repository, RepositoryViewHolder> {

    private PagedList<Repository> repositoryList;
    private Context context;

    public RepositoryAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    public static final DiffUtil.ItemCallback<Repository> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Repository>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull Repository oldUser, @NonNull Repository newUser) {
                    // User properties may have changed if reloaded from the DB, but ID is fixed
                    return oldUser.getId() == newUser.getId();
                }
                @Override
                public boolean areContentsTheSame(
                        @NonNull Repository oldUser, @NonNull Repository newUser) {
                    // NOTE: if you use equals, your object must properly override Object#equals()
                    // Incorrectly returning false here will result in too many animations.
                    return oldUser.equals(newUser);
                }
            };

//    public RepositoryAdapter(ArrayList<Repository> events, Context context, boolean isDesire){
//        this.repositoryList = events;
//        this.context = context;
//        this.isDesire = isDesire;
//    }

    @NonNull
    @Override
    public RepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.repository_item, viewGroup, false);
        return new RepositoryViewHolder(view);
    }

//    @Nullable
//    @Override
//    protected Repository getItem(int position) {
//        return super.getItem(position);
//    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryViewHolder repositoryViewHolder, int i) {

        Repository repository = getItem(i);
        repositoryViewHolder.setRepository(repository);

        if (repository != null) {
            repositoryViewHolder.repoName.setText(repository.getRepoName());
            repositoryViewHolder.repoDescription.setText(repository.getRepoDescription());
            repositoryViewHolder.userFullname.setText(repository.getFullName());
            repositoryViewHolder.username.setText(repository.getUsername());
            repositoryViewHolder.starsQuantity.setText(String.valueOf(repository.getStarsQuantity()));
            repositoryViewHolder.forkQuantity.setText(String.valueOf(repository.getForkQuantity()));
            Picasso.get().load(repository.getPictureUrl()).into(repositoryViewHolder.profilePicture);
        } else {

        }

    }
}
