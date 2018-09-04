package com.example.vladi.consultagit.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vladi.consultagit.R;
import com.example.vladi.consultagit.models.Repository;
import com.example.vladi.consultagit.ui.PullsActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.RepositoriesViewHolder>{

    private List<Repository> repositoriesList;
    private Context context;

    public RepositoriesAdapter(List<Repository> repositoriesList, Context context) {
        this.repositoriesList = repositoriesList;
        this.context = context;
    }

    @NonNull
    @Override
    public RepositoriesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,viewGroup,false);
        return new RepositoriesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final RepositoriesViewHolder repositoriesViewHolder, int i) {
        final Repository repository = repositoriesList.get(i);

        repositoriesViewHolder.repositoryName.setText(repository.getName());
        repositoriesViewHolder.repositoryDescription.setText(repository.getDescription());
        Glide.with(context)
                .load(repository.getOwner().getAvatar())
                .crossFade()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(repositoriesViewHolder.authorImage);
        repositoriesViewHolder.authorName.setText(repository.getOwner().getLogin());
        repositoriesViewHolder.forksCount.setText(repository.getForks());
        repositoriesViewHolder.starsCount.setText(repository.getStars());

        repositoriesViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PullsActivity.class);
                intent.putExtra("Owner", repository.getOwner().getLogin());
                intent.putExtra("Name", repository.getName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return repositoriesList.size();
    }

    public class RepositoriesViewHolder extends RecyclerView.ViewHolder{

        public TextView repositoryName;
        public TextView repositoryDescription;
        public CircleImageView authorImage;
        public TextView authorName;
        public TextView forksCount;
        public TextView starsCount;
        public ImageView forksIcon;
        public ImageView starsIcon;
        public LinearLayout parentLayout;

            public RepositoriesViewHolder(@NonNull View itemView) {
                super(itemView);
                parentLayout = (LinearLayout)itemView.findViewById(R.id.item_layout);
                repositoryName = (TextView)itemView.findViewById(R.id.name);
                repositoryDescription = (TextView)itemView.findViewById(R.id.description);
                authorImage = (CircleImageView) itemView.findViewById(R.id.author_image);
                authorName = (TextView)itemView.findViewById(R.id.author_name);
                forksCount = (TextView)itemView.findViewById(R.id.fork_number);
                starsCount = (TextView)itemView.findViewById(R.id.stars_state);
                forksIcon = (ImageView)itemView.findViewById(R.id.fork_icon);
                starsIcon = (ImageView)itemView.findViewById(R.id.stars_icon);
            }
        }
}
