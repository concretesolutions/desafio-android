package br.com.githubrepos.pullrequests;

import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import br.com.githubrepos.R;
import br.com.githubrepos.commons.ItemListListener;
import br.com.githubrepos.data.entity.PullRequest;

public class PullRequestsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private LayoutInflater mInflater;
    //CopyOnWriteArrayList - http://docs.oracle.com/javase/7/docs/api/java/util/concurrent/CopyOnWriteArrayList.html
    //CopyOnWriteArrayList - http://stackoverflow.com/a/26281967
    private CopyOnWriteArrayList<PullRequest> mDataList;
    private ItemListListener<PullRequest> mItemListener;
    private RecyclerView mRecyclerView;

    public PullRequestsAdapter(List<PullRequest> pullRequestList, RecyclerView recyclerView,
                               ItemListListener<PullRequest> pullRequestItemListListener) {

        this.mDataList = new CopyOnWriteArrayList<>();
        this.mDataList.addAll(0, pullRequestList);
        this.mItemListener = pullRequestItemListListener;

        this.mRecyclerView = recyclerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (null == mInflater) {
            mInflater = LayoutInflater.from(parent.getContext());
        }

        if (viewType == TYPE_ITEM) {
            View pullRequestView = mInflater.inflate(R.layout.item_pullrequest, parent, false);
            return new PullRequestsAdapter.ItemViewHolder(pullRequestView, mItemListener);
        } else {
            View headerView = mInflater.inflate(R.layout.item_pullrequest_header, parent, false);
            return new PullRequestsAdapter.HeaderViewHolder(headerView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PullRequestsAdapter.ItemViewHolder) {

            PullRequestsAdapter.ItemViewHolder itemViewHolder = (PullRequestsAdapter.ItemViewHolder) holder;
            final PullRequest pullRequest = mDataList.get(position);

            /*if (null != pullRequest.getOwner().getAvatarUrl() &&
                    !"".equals(pullRequest.getOwner().getAvatarUrl())) {

                Picasso.with(itemViewHolder.ivOwnerPicture.getContext())
                        .load(pullRequest.getOwner().getAvatarUrl())
                        .transform(new CircleTransform())
                        .fit().centerCrop()
                        .placeholder(R.drawable.ic_avatar)
                        .into(itemViewHolder.ivOwnerPicture);
            } else {
                itemViewHolder.ivOwnerPicture.setImageResource(R.drawable.ic_avatar);
            }

            itemViewHolder.tvRepositoryName.setText(pullRequest.getName());
            itemViewHolder.tvRepositoryDescription.setText(pullRequest.getDescription());
            itemViewHolder.tvRepositoryForksCount.setText(String.format("%d", pullRequest.getForksCount()));
            itemViewHolder.tvRepositoryStargazersCount.setText(String.format("%d", pullRequest.getStargazersCount()));
            itemViewHolder.tvOwnerLogin.setText(pullRequest.getOwner().getLogin());
            itemViewHolder.tvOwnerFullname.setText(pullRequest.getOwner().getLogin());*/
            //itemViewHolder.tvOwnerFullname.setText(repository.getOwner().getName());
        } else {
            //TODO fazer o header
            //((PullRequestsAdapter.HeaderViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mDataList.get(position) != null ? TYPE_ITEM : TYPE_HEADER;
    }

    public PullRequest getItem(int position) {
        return mDataList.get(position);
    }

    public void replaceData(List<PullRequest> pullRequestList) {
        int size = this.mDataList.size();
        this.mDataList.clear();
        notifyItemRangeRemoved(0, size);

        this.mDataList.addAll(pullRequestList);
        notifyDataSetChanged();
    }

    public void addData(List<PullRequest> pullRequestList) {
        int size = this.mDataList.size();
        this.mDataList.clear();
        notifyItemRangeRemoved(0, size);

        this.mDataList.addAll(pullRequestList);
        notifyDataSetChanged();
    }

    public CopyOnWriteArrayList<PullRequest> getList() {
        return this.mDataList;
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        //ProgressBar progressBar;

        HeaderViewHolder(View itemView) {
            super(itemView);

            //progressBar = itemView.findViewById(R.id.progress_bar);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemListListener<PullRequest> mItemListener;

        ConstraintLayout mLayout;
        /*TextView tvRepositoryName;
        TextView tvRepositoryDescription;
        TextView tvRepositoryForksCount;
        TextView tvRepositoryStargazersCount;
        ImageView ivOwnerPicture;
        TextView tvOwnerLogin;
        TextView tvOwnerFullname;*/

        ItemViewHolder(View itemView, final ItemListListener<PullRequest> itemListener) {
            super(itemView);
            this.mItemListener = itemListener;

            mLayout = itemView.findViewById(R.id.cl_item_repository);
            /*tvRepositoryName = itemView.findViewById(R.id.tv_repository_name);
            tvRepositoryDescription = itemView.findViewById(R.id.tv_repository_description);
            tvRepositoryForksCount = itemView.findViewById(R.id.tv_repository_forks_count);
            tvRepositoryStargazersCount = itemView.findViewById(R.id.tv_repository_stargazers_count);

            ivOwnerPicture = itemView.findViewById(R.id.iv_owner_picture);
            tvOwnerLogin = itemView.findViewById(R.id.tv_owner_login);
            tvOwnerFullname = itemView.findViewById(R.id.tv_owner_fullname);*/

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mItemListener.onClickedItem(getItem(getAdapterPosition()));
        }
    }
}
