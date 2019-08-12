package com.example.desafioandroid.feature.repository;

import android.content.Context;
import android.text.Layout;
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
import com.example.desafioandroid.model.Base;
import com.example.desafioandroid.model.Repository;

import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryAdapterViewHolder> {

    private List<Repository> mRepositoryList;
    private RepositoryAdapterOnClickListener mRepositoryClick;
    private Context mContext;

    public interface RepositoryAdapterOnClickListener {
        void onMyClickListener(Repository repository);
    }

    public RepositoryAdapter(List<Repository> mRepositoryList,
                             RepositoryAdapterOnClickListener repositoryAdapterOnClickListener,
                             Context context) {
        this.mRepositoryList = mRepositoryList;
        this.mRepositoryClick = repositoryAdapterOnClickListener;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RepositoryAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int idLayoutForInflate = R.layout.item_repository;
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(idLayoutForInflate, parent, false);
        RepositoryAdapterViewHolder viewHolder = new RepositoryAdapterViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryAdapterViewHolder holder, int position) {
        Repository repository = mRepositoryList.get(position);

        holder.mRepositoryName.setText(repository.getName());
        holder.mRepositoryDescription.setText(repository.getDescription());
        holder.mRepositoryUsername.setText(repository.getOwner().getLogin());
        holder.mStarRepository.setText(repository.getStargazers_count()+"");
        holder.mFolkRepository.setText(repository.getForks()+"");

        RequestOptions requestOptions = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL).circleCrop();

        Glide.with(mContext)
                .asBitmap()
                .load(repository.getOwner().getAvatar_url())
                .apply(requestOptions)
                .into(holder.mImageRepository);
    }

    @Override
    public int getItemCount() {
        if (mRepositoryList != null) {
            return mRepositoryList.size();
        } else {
            return 0;
        }
    }

    public void updateItems(List<Repository> items){
        this.mRepositoryList = items;
        notifyDataSetChanged();
    }

    public void updateItems2(List<Repository> items){
        for (int i=0; i<items.size(); i++) {
            Repository repository = items.get(i);
            this.mRepositoryList.add(repository);
            notifyDataSetChanged();
        }
    }

    public class RepositoryAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mRepositoryName, mRepositoryDescription, mRepositoryUsername , mStarRepository, mFolkRepository;
        public ImageView mImageRepository;

        public RepositoryAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            this.mRepositoryName = itemView.findViewById(R.id.tvRepository);
            this.mRepositoryDescription = itemView.findViewById(R.id.tvRepositoryDescription);
            this.mRepositoryUsername = itemView.findViewById(R.id.tvUsername);
            this.mImageRepository = itemView.findViewById(R.id.imageViewRepository);
            this.mStarRepository = itemView.findViewById(R.id.tvStarRepository);
            this.mFolkRepository = itemView.findViewById(R.id.tvForkRepository);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Repository repository = mRepositoryList.get(position);
            mRepositoryClick.onMyClickListener(repository);
        }
    }
}
