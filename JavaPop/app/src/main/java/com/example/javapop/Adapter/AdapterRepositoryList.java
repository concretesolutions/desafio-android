package com.example.javapop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javapop.Events.ClickItemRepositoryEvent;
import com.example.javapop.Models.Repository;
import com.example.javapop.R;
import com.example.javapop.Utils.CircleTransform;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdapterRepositoryList extends RecyclerView.Adapter<AdapterRepositoryList.ViewHolderRepository> {

    List<Repository> mRepositories;
    private Context mContext;

    public static class ViewHolderRepository extends RecyclerView.ViewHolder {

        @BindView(R.id.name_repository)
        TextView mNameRepository;

        @BindView(R.id.description_repository)
        TextView mDescriptionRepository;

        @BindView(R.id.fork_number)
        TextView mForkNumber;

        @BindView(R.id.star_number)
        TextView mStarNumber;

        @BindView(R.id.name_username)
        TextView mNameUsername;

        @BindView(R.id.name_lastname)
        TextView mNameLastname;

        @BindView(R.id.image_user)
        ImageView mImageUser;

        Repository mRepository;

        @OnClick(R.id.item_repository)
        void itemRepository() {
            EventBus.getDefault().post(new ClickItemRepositoryEvent(mRepository, getAdapterPosition()));
        }


        public ViewHolderRepository(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void getRepository(Repository repository) {
            this.mRepository = repository;
        }
    }

    public AdapterRepositoryList(List<Repository> mRepositories, Context context) {
        this.mRepositories = mRepositories;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolderRepository onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_repository_list, parent, false);
        return new ViewHolderRepository(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRepository holder, int position) {
        final Repository repository = mRepositories.get(position);
        holder.mNameRepository.setText(repository.getNameRepository());
        holder.mDescriptionRepository.setText(repository.getDescriptionRepository());
        holder.mForkNumber.setText(String.valueOf(repository.getForkNumber()));
        holder.mNameUsername.setText(repository.getOwnerGit().getUsername());
        Picasso.with(mContext).load(repository.getOwnerGit().getURLimage()).
                transform(new CircleTransform()).into(holder.mImageUser);
        holder.mStarNumber.setText(String.valueOf(repository.getStarsNumber()));
        holder.mNameLastname.setText(repository.getLastName());

        holder.getRepository(repository);
    }

    @Override
    public int getItemCount() {
        return mRepositories.size();
    }

}
