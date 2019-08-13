package com.desafioconcret.adapters;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.desafioconcret.R;
import com.desafioconcret.pojo.json.PullRequests;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PullAdapter extends RecyclerView.Adapter<PullAdapter.ViewHolder>{

    private List<PullRequests> pullRequestses;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView pullTitle;
        public TextView pullDescription;
        public CircleImageView pullUserAvatar;
        public TextView pullUser;
        public TextView pullDate;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formatedDate = dateFormat.format(dateFormat);


        public ViewHolder(final View view) {
            super(view);
            this.pullTitle = (TextView) view.findViewById(R.id.pull_title);
            this.pullDescription = (TextView) view.findViewById(R.id.pull_description);
            this.pullUserAvatar = (CircleImageView) view.findViewById(R.id.circle_image);
            this.pullUser = (TextView) view.findViewById(R.id.pull_user);
            this.pullDate = (TextView) view.findViewById(R.id.pull_date);


        }
    }

    public PullAdapter (List<PullRequests> pullRequestses) {
        this.pullRequestses = pullRequestses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pull_view_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.pullTitle = (TextView) view.findViewById(R.id.pull_title);
        viewHolder.pullDescription = (TextView) view.findViewById(R.id.pull_description);
        viewHolder.pullUserAvatar = (CircleImageView) view.findViewById(R.id.circle_image);
        viewHolder.pullUser = (TextView) view.findViewById(R.id.pull_user);
        viewHolder.pullDate = (TextView) view.findViewById(R.id.pull_date);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        viewHolder.pullTitle.setText(this.pullRequestses.get(position).getTitle());
        viewHolder.pullDescription.setText(this.pullRequestses.get(position).getBody());
        Picasso.with(viewHolder.pullUserAvatar.getContext()).load(this.pullRequestses.get(position)
                .getUser().getAvatarUrl()).fit().centerCrop().into(viewHolder.pullUserAvatar);
        viewHolder.pullUser.setText(this.pullRequestses.get(position).getUser().getLogin());
        viewHolder.pullDate.setText((CharSequence) this.pullRequestses.get(position).getCreated_at());


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = pullRequestses.get(position).getHtmlUrl();
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                v.getContext().startActivity(intent);

            }
        });

    }
    @Override
    public int getItemCount() {
        return pullRequestses != null ? pullRequestses.size() : 0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
