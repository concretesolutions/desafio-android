package com.example.github_api_concrete.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.github_api_concrete.R;
import com.example.github_api_concrete.model.pojo.pullrequests.Response;
import com.example.github_api_concrete.view.interfaces.OnClickPR;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PullRequestsRecyclerViewAdapter extends RecyclerView.Adapter<PullRequestsRecyclerViewAdapter.ViewHolder> {

    private List<Response> pullRequestList;
    private OnClickPR listener;

    public PullRequestsRecyclerViewAdapter(List<Response> pullRequestList, OnClickPR listener){
        this.pullRequestList = pullRequestList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_pr, parent, false);
        return new PullRequestsRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Response response = pullRequestList.get(position);
        holder.onBind(response);
        holder.itemView.setOnClickListener(v -> listener.click(response));
    }

    @Override
    public int getItemCount() {
        return pullRequestList.size();
    }

    public void updateList(List<Response> newPRList) {
        this.pullRequestList.clear();
        this.pullRequestList = newPRList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView userIcon;
        private TextView namePR;
        private TextView descriptionPR;
        private TextView datePR;
        private TextView username;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userIcon = itemView.findViewById(R.id.usericon2);
            namePR = itemView.findViewById(R.id.name2);
            descriptionPR = itemView.findViewById(R.id.description2);
            datePR = itemView.findViewById(R.id.creation_date);
            username = itemView.findViewById(R.id.username2);
        }

        public void onBind(Response response) {
            Picasso.get()
                    .load(response.getUser().getAvatarUrl())
                    .placeholder(R.drawable.usericon)
                    .into(userIcon);

            namePR.setText(response.getTitle());

            if (response.getBody().isEmpty()) {
                descriptionPR.setText("No description provided.");
            } else {
                descriptionPR.setText(response.getBody());
            }

            if (response.getCreatedAt() == null || response.getCreatedAt().isEmpty()){
                datePR.setText("No creation date provided.");
            } else {
                String dateISO = response.getCreatedAt().split("T")[0];
                String[] dates = dateISO.split("-");
                String creationDate = dates[2] + "/" + dates[1] + "/" + dates[0];
                datePR.setText(creationDate);
            }

            username.setText(response.getUser().getLogin());
        }
    }
}


