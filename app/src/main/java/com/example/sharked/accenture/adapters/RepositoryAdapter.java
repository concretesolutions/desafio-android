package com.example.sharked.accenture.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sharked.accenture.R;
import com.example.sharked.accenture.holders.RepositoryHolder;
import com.example.sharked.accenture.models.Repository;
import com.example.sharked.accenture.views.activities.PullRequestListActivity;
import com.example.sharked.accenture.views.activities.PullRequestListActivity_;

import java.util.ArrayList;


public class RepositoryAdapter extends  RecyclerView.Adapter<RepositoryHolder>  {
    private Context ctx;
    private ArrayList<Repository> items;
    public RepositoryAdapter(@NonNull Context context, @NonNull ArrayList<Repository> objects) {
        ctx = context;
        items = objects;
    }

    @Override
    public RepositoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.li_repository, parent, false);
        return new RepositoryHolder(view);
    }

    @Override
    public void onBindViewHolder(RepositoryHolder holder, int position) {
        Repository item = items.get(position);

        //Despliego en pantalla
        holder.repositoryNameTV.             setText(item.getName());
        holder.repositoryStarTV.             setText(item.getStargazersCount());
        holder.repositoryPRTV.               setText(item.getForks());
        holder.repositoryOwnerTV.            setText(item.getOwner().getLogin());
        holder.repositoryOwnerNickTV.        setText(item.getOwner().getType());
        holder.repositoryDescriptionTV.      setText(item.getDescription());

        //Asigno onClick
        holder.repositoryContainerLL.setOnClickListener(getActionListener(item));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    private View.OnClickListener getActionListener(final Repository repo){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Envio el objeto serializado en caso de ser necesaria mas info
                Intent intent;
                intent = new Intent(ctx, PullRequestListActivity_.class);
                intent.putExtra(PullRequestListActivity.REPOSITORY_EXTRA, repo);
                ctx.startActivity(intent);
            }
        };
    }

}
