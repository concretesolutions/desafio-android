package br.com.githubrepos.repositories;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import br.com.githubrepos.R;
import br.com.githubrepos.commons.EndlessScrolling;
import br.com.githubrepos.commons.ItemListListener;
import br.com.githubrepos.data.entity.Repository;
import br.com.githubrepos.util.CircleTransform;

//Visible only in this package
class RepositoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 1;
    private static final int TYPE_LOADING = 0;

    private LayoutInflater mInflater;
    //CopyOnWriteArrayList - http://docs.oracle.com/javase/7/docs/api/java/util/concurrent/CopyOnWriteArrayList.html
    //CopyOnWriteArrayList - http://stackoverflow.com/a/26281967
    private CopyOnWriteArrayList<Repository> mDataList;
    private ItemListListener<Repository> mItemListener;
    private EndlessScrolling mEndlessScrolling;
    private RecyclerView mRecyclerView;

    private int selectedPosition;

    public RepositoriesAdapter(List<Repository> repositoryList, RecyclerView recyclerView,
                               ItemListListener<Repository> repositoryItemListener,
                               final EndlessScrolling.OnLoadMoreListener onLoadMoreListener) {

        this.mDataList = new CopyOnWriteArrayList<>();
        this.mDataList.addAll(0, repositoryList);
        this.mItemListener = repositoryItemListener;

        this.mEndlessScrolling = new EndlessScrolling(recyclerView.getLayoutManager(), onLoadMoreListener);
        this.mRecyclerView = recyclerView;
        this.mRecyclerView.addOnScrollListener(mEndlessScrolling);

        selectedPosition = -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (null == mInflater) {
            mInflater = LayoutInflater.from(parent.getContext());
        }

        if (viewType == TYPE_ITEM) {
            View repositoryView = mInflater.inflate(R.layout.item_repository, parent, false);
            return new RepositoriesAdapter.ItemViewHolder(repositoryView, mItemListener);
        } else {
            View loadingView = mInflater.inflate(R.layout.item_loading, parent, false);
            return new RepositoriesAdapter.ProgressViewHolder(loadingView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RepositoriesAdapter.ItemViewHolder) {

            RepositoriesAdapter.ItemViewHolder itemViewHolder = (RepositoriesAdapter.ItemViewHolder) holder;
            final Repository repository = mDataList.get(position);

            if (selectedPosition == position) {
                itemViewHolder.mLayout.setBackgroundColor(Color.parseColor("#eeeeee"));
            } else {
                itemViewHolder.mLayout.setBackgroundColor(Color.parseColor("#ffffff"));
            }

            if (null != repository.getOwner().getAvatarUrl() &&
                    !"".equals(repository.getOwner().getAvatarUrl())) {

                Picasso.with(itemViewHolder.ivOwnerPicture.getContext())
                        .load(repository.getOwner().getAvatarUrl())
                        .transform(new CircleTransform())
                        .fit().centerCrop()
                        .placeholder(R.drawable.ic_avatar)
                        .into(itemViewHolder.ivOwnerPicture);
            } else {
                itemViewHolder.ivOwnerPicture.setImageResource(R.drawable.ic_avatar);
            }

            itemViewHolder.tvRepositoryName.setText(repository.getName());
            itemViewHolder.tvRepositoryDescription.setText(repository.getDescription());
            itemViewHolder.tvRepositoryForksCount.setText(String.format("%d", repository.getForksCount()));
            itemViewHolder.tvRepositoryStargazersCount.setText(String.format("%d", repository.getStargazersCount()));
            itemViewHolder.tvOwnerLogin.setText(repository.getOwner().getLogin());
            itemViewHolder.tvOwnerFullname.setText(repository.getOwner().getLogin());
            //itemViewHolder.tvOwnerFullname.setText(repository.getOwner().getName());
        } else {
            ((RepositoriesAdapter.ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mDataList.get(position) != null ? TYPE_ITEM : TYPE_LOADING;
    }

    public int getPage() {
        return mEndlessScrolling.getCurrentPage();
    }

    public void setPage(int page) {
        mEndlessScrolling.setCurrentPage(page);
    }

    public Repository getItem(int position) {
        return mDataList.get(position);
    }

    public void removeItem(int position) {
        mDataList.remove(position);
        notifyItemRemoved(position);
    }

    public void addProgressItem() {
        this.mDataList.add(null);
        notifyItemInserted(getItemCount() - 1);
    }

    public void removeProgressItem() {
        this.mDataList.remove(getItemCount() - 1);
        notifyItemRemoved(getItemCount() - 1);
        mEndlessScrolling.loaded();
    }

    public void unselectItem(int index) {
        if (index > -1) {
            RecyclerView.ViewHolder view = mRecyclerView.findViewHolderForLayoutPosition(index);
            if (view.getItemViewType() == TYPE_ITEM) {
                ItemViewHolder itemViewHolder = (ItemViewHolder) view;
                itemViewHolder.mLayout.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }
        selectedPosition = -1;
    }

    public void replaceData(List<Repository> repositoryList) {
        int size = this.mDataList.size();
        this.mDataList.clear();
        notifyItemRangeRemoved(0, size);

        this.mDataList.addAll(repositoryList);
        notifyDataSetChanged();

        mEndlessScrolling.resetPagination();
    }

    public void appendData(List<Repository> repositoryList) {
        this.mDataList.addAll(getItemCount(), repositoryList);
        notifyDataSetChanged();
    }

    public void addData(List<Repository> repositoryList) {
        int size = this.mDataList.size();
        this.mDataList.clear();
        notifyItemRangeRemoved(0, size);

        this.mDataList.addAll(repositoryList);
        notifyDataSetChanged();
    }

    public CopyOnWriteArrayList<Repository> getList() {
        return this.mDataList;
    }

    class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View itemView) {
            super(itemView);

            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar);
        }
    }

    //Nested classes - http://docs.oracle.com/javase/tutorial/java/javaOO/nested.html
    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnLongClickListener {

        private ItemListListener<Repository> mItemListener;

        //TODO butterknife aparentemente gerou queda de performance
        //@BindView(R.id.ll_item_repository)
        public LinearLayout mLayout;

        //TODO butterknife aparentemente gerou queda de performance
        //@BindView(R.id.tv_repository_name)
        public TextView tvRepositoryName;

        //TODO butterknife aparentemente gerou queda de performance
        //@BindView(R.id.tv_repository_description)
        public TextView tvRepositoryDescription;

        //TODO butterknife aparentemente gerou queda de performance
        //@BindView(R.id.tv_repository_forks_count)
        public TextView tvRepositoryForksCount;

        //TODO butterknife aparentemente gerou queda de performance
        //@BindView(R.id.tv_repository_stargazers_count)
        public TextView tvRepositoryStargazersCount;

        //TODO butterknife aparentemente gerou queda de performance
        //@BindView(R.id.iv_owner_picture)
        public ImageView ivOwnerPicture;

        //TODO butterknife aparentemente gerou queda de performance
        //@BindView(R.id.tv_owner_login)
        public TextView tvOwnerLogin;

        //TODO butterknife aparentemente gerou queda de performance
        //@BindView(R.id.tv_owner_fullname)
        public TextView tvOwnerFullname;

        public ItemViewHolder(View itemView, final ItemListListener<Repository> itemListener) {
            super(itemView);
            this.mItemListener = itemListener;

            mLayout = (LinearLayout) itemView.findViewById(R.id.ll_item_repository);
            tvRepositoryName = (TextView) itemView.findViewById(R.id.tv_repository_name);
            tvRepositoryDescription = (TextView) itemView.findViewById(R.id.tv_repository_description);
            tvRepositoryForksCount = (TextView) itemView.findViewById(R.id.tv_repository_forks_count);
            tvRepositoryStargazersCount = (TextView) itemView.findViewById(R.id.tv_repository_stargazers_count);

            ivOwnerPicture = (ImageView) itemView.findViewById(R.id.iv_owner_picture);
            tvOwnerLogin = (TextView) itemView.findViewById(R.id.tv_owner_login);
            tvOwnerFullname = (TextView) itemView.findViewById(R.id.tv_owner_fullname);

            //TODO butterknife aparentemente gerou queda de performance
            //ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mItemListener.onClickedItem(getItem(getAdapterPosition()));
        }

        @Override
        public boolean onLongClick(View v) {
            int adapterPosition = getAdapterPosition();

            if (selectedPosition != adapterPosition) {
                selectedPosition = adapterPosition;
                notifyDataSetChanged();
                mItemListener.onLongClickedSelectItem(selectedPosition);
            } else {
                selectedPosition = -1;
                notifyDataSetChanged();
                mItemListener.onLongClickedUnselectItem(selectedPosition);
            }
            return true;
        }
    }
}