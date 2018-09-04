package com.example.vladi.consultagit.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vladi.consultagit.R;
import com.example.vladi.consultagit.models.PullRequest;
import com.example.vladi.consultagit.ui.PullRequestActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PullRequestsAdapter  extends RecyclerView.Adapter<PullRequestsAdapter.PullRequestsViewHolder> {

    private List<PullRequest> pullRequestList;
    private Context context;

    public PullRequestsAdapter(List<PullRequest> pullRequestList, Context context) {
        this.pullRequestList = pullRequestList;
        this.context = context;
    }

    @NonNull
    @Override
    public PullRequestsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,viewGroup,false);
        return new PullRequestsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PullRequestsViewHolder pullRequestsViewHolder, int i) {

        final PullRequest pullRequests = pullRequestList.get(i);

        pullRequestsViewHolder.pullRequestName.setText(pullRequests.getTitle());
        pullRequestsViewHolder.pullRequestDescription.setText(pullRequests.getBody());
        Glide.with(context)
                .load(pullRequests.getOwner().getAvatar())
                .crossFade()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(pullRequestsViewHolder.authorImage);
        pullRequestsViewHolder.authorName.setText(pullRequests.getOwner().getLogin());
        pullRequestsViewHolder.pullNumber.setText(pullRequests.getNumber());
        pullRequestsViewHolder.state.setText(pullRequests.getState());

        pullRequestsViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Aqui comienza la activity 4 -> Ver un Pull Request en específico, PullRequestActivity
                //Intent intent = new Intent(context, PullRequestActivity.class);
                //context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return pullRequestList.size();
    }


    public class PullRequestsViewHolder extends RecyclerView.ViewHolder{

        public TextView pullRequestName;
        public TextView pullRequestDescription;
        public CircleImageView authorImage;
        public TextView authorName;
        public TextView pullNumber;
        public TextView state;
        public LinearLayout parentLayout;

        public PullRequestsViewHolder(@NonNull View itemView) {
            super(itemView);

            parentLayout = (LinearLayout)itemView.findViewById(R.id.item_layout);
            pullRequestName = (TextView)itemView.findViewById(R.id.name);
            pullRequestDescription = (TextView)itemView.findViewById(R.id.description);
            authorImage = (CircleImageView) itemView.findViewById(R.id.author_image);
            authorName = (TextView)itemView.findViewById(R.id.author_name);
            pullNumber = (TextView)itemView.findViewById(R.id.fork_number);
            state = (TextView)itemView.findViewById(R.id.stars_state);
        }
    }
}
