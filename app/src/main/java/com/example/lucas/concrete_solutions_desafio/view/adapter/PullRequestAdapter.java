package com.example.lucas.concrete_solutions_desafio.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.lucas.concrete_solutions_desafio.R;
import com.example.lucas.concrete_solutions_desafio.model.PullRequest;
import com.example.lucas.concrete_solutions_desafio.model.PullRequestList;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import de.hdodenhof.circleimageview.CircleImageView;

public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.ViewHolderPullRequest> {
    private final Context context;
    private PullRequestList pullRequests;

    public PullRequestAdapter(PullRequestList pullRequests, Context context) {
        this.context = context;
        this.pullRequests = pullRequests;
    }

    @NonNull
    @Override
    public PullRequestAdapter.ViewHolderPullRequest onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.pull_request_list, parent, false);
        ViewHolderPullRequest holderPullRequest = new ViewHolderPullRequest(view);
        return holderPullRequest;
    }

    @Override
    public void onBindViewHolder(@NonNull PullRequestAdapter.ViewHolderPullRequest holder, int position) {

        if ((pullRequests != null) && (pullRequests.pullRequestsCount() > 0)) {
            final PullRequest pullRequest = pullRequests.getPullRequestByPosition(position);
            holder.bind(pullRequest);
        }

    }

    @Override
    public int getItemCount() {
        return pullRequests.pullRequestsCount();
    }

    public class ViewHolderPullRequest extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView body;
        public TextView created_at;
        public TextView login;
        public CircleImageView avatar_url;

        public ViewHolderPullRequest(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.pullRequest_tvPullRequestTitle);
            body =  itemView.findViewById(R.id.pullRequest_tvPullRequestDescription);
            created_at = itemView.findViewById(R.id.pullRequest_tvPullRequestDate);
            login =  itemView.findViewById(R.id.pullRequest_tvPullRequestOwnerName);
            avatar_url = itemView.findViewById(R.id.pullRequest_ivPullRequestOwnerPicture);
        }

        public void bind(PullRequest pullRequest) {
            String empty = "";
            String date;

            if(pullRequest.getUser().getAvatar_url()!=empty) {
                Picasso.get().load(pullRequest.getUser().getAvatar_url()).into(avatar_url);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                date = sdf.format(pullRequest.getCreated_at().getTime());
            }
            else{
                date = "";
            }
            title.setText(pullRequest.getTitle());
            login.setText(pullRequest.getUser().getLogin());
            body.setText(pullRequest.getBody());
            created_at.setText(date);
        }
    }
}
