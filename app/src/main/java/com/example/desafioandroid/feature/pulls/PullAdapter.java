package com.example.desafioandroid.feature.pulls;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.desafioandroid.R;
import com.example.desafioandroid.model.Pull;

import java.util.List;

public class PullAdapter extends RecyclerView.Adapter<PullAdapter.PullAdapterViewHolder> {

    private List<Pull> mPullsList;
    private Context mContext;
    private PullAdapterOnClickListener mPullAdapterOnClick;

    public interface PullAdapterOnClickListener {
        void onMyClickListener(Pull pull);
    }

    public PullAdapter(List<Pull> mPulls, Context mContext, PullAdapterOnClickListener mPullAdapterOnClick) {
        this.mPullsList = mPulls;
        this.mContext = mContext;
        this.mPullAdapterOnClick = mPullAdapterOnClick;
    }

    @NonNull
    @Override
    public PullAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int idLayoutForInflate = R.layout.item_pull;
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(idLayoutForInflate, parent, false);
        PullAdapterViewHolder viewHolder = new PullAdapterViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PullAdapterViewHolder holder, int position) {
        Pull pull = mPullsList.get(position);

        holder.mPullTitle.setText(pull.getTitle());
        holder.mPullDescription.setText(pull.getBody());
        holder.mPullUsername.setText(pull.getOwner().getLogin());
        holder.mPullDate.setText(pull.getCreated_at().substring(0,10));

        RequestOptions requestOptions = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL).circleCrop();

        Glide.with(mContext)
                .asBitmap()
                .load(pull.getOwner().getAvatar_url())
                .apply(requestOptions)
                .into(holder.mImagePull);
    }

    @Override
    public int getItemCount() {
        if (mPullsList != null) {
            return mPullsList.size();
        } else {
            return 0;
        }
    }

    public void updateItems(List<Pull> items){
        this.mPullsList = items;
        notifyDataSetChanged();
    }

    public class PullAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mPullTitle, mPullDescription, mPullUsername, mPullDate;
        public ImageView mImagePull;

        public PullAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            this.mPullTitle = itemView.findViewById(R.id.tvPullTitle);
            this.mPullDescription = itemView.findViewById(R.id.tvPullDescription);
            this.mPullUsername = itemView.findViewById(R.id.tvPullUsername);
            this.mPullDate = itemView.findViewById(R.id.tvPullDate);
            this.mImagePull = itemView.findViewById(R.id.imageViewPull);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Pull pull = mPullsList.get(position);
            mPullAdapterOnClick.onMyClickListener(pull);
        }
    }
}
