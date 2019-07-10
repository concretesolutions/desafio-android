package com.example.javapop.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javapop.Events.ClickItemPullRequestEvent;
import com.example.javapop.Models.PullRequest;
import com.example.javapop.Models.PullRequestList;
import com.example.javapop.R;
import com.example.javapop.Utils.CircleTransform;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdapterPullRequest extends RecyclerView.Adapter<AdapterPullRequest.ViewHolderRepository> {

    private List<PullRequestList> pullRequests;
    private Context mContext;

    public static class ViewHolderRepository extends RecyclerView.ViewHolder {

        @BindView(R.id.title_PR)
        TextView mTitlePR;

        @BindView(R.id.description_PR)
        TextView mDescriptionPR;

        @BindView(R.id.image_user_PR)
        ImageView mImagePR;

        @BindView(R.id.name_username_PR)
        TextView mNameUsernamePR;

        @BindView(R.id.date_PR)
        TextView mDatePR;

        PullRequest mPullRequest;

        @OnClick(R.id.item_pull_request)
        void itemPullRequest() {
            EventBus.getDefault().post(new ClickItemPullRequestEvent(mPullRequest, getAdapterPosition()));
        }

        public ViewHolderRepository(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void getPullRequest(PullRequest pullRequest) {
            this.mPullRequest = pullRequest;
        }
    }

    public AdapterPullRequest(List<PullRequestList> pullRequest, Context context) {
        this.pullRequests = pullRequest;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolderRepository onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_pull_request, parent, false);
        return new ViewHolderRepository(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRepository holder, int position) {
        PullRequest pullRequest = pullRequests.get(position);

        holder.mTitlePR.setText(pullRequest.getTitlePR());
        holder.mDescriptionPR.setText(pullRequest.getDescriptionPR());
        Picasso.with(mContext).load(pullRequest.getOwnerGit().getURLimage()).
                transform(new CircleTransform()).into(holder.mImagePR);
        holder.mNameUsernamePR.setText(pullRequest.getOwnerGit().getUsername());
        holder.mDatePR.setText(dateFormat(pullRequest.getDatePR()));

        holder.getPullRequest(pullRequest);
    }

    @Override
    public int getItemCount() {
        return pullRequests.size();
    }

    @SuppressLint("SimpleDateFormat")
    private String dateFormat(String stringDate) {
        try {
            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            date = sdf.parse(stringDate);
            return new SimpleDateFormat("dd/MM/yyyy").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}

