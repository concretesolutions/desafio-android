package br.com.githubrepos.pullrequests;

import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import br.com.githubrepos.R;
import br.com.githubrepos.commons.ItemListListener;
import br.com.githubrepos.data.entity.PullRequest;
import br.com.githubrepos.util.CircleTransform;

public class PullRequestsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private LayoutInflater mInflater;
    private CopyOnWriteArrayList<PullRequest> mDataList;
    private ItemListListener<PullRequest> mItemListener;

    public PullRequestsAdapter(List<PullRequest> pullRequestList, RecyclerView recyclerView,
                               ItemListListener<PullRequest> pullRequestItemListListener) {

        this.mDataList = new CopyOnWriteArrayList<>();
        this.mDataList.addAll(0, pullRequestList);
        this.mItemListener = pullRequestItemListListener;
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

            if (null != pullRequest.getUser().getAvatarUrl() && !pullRequest.getUser().getAvatarUrl().isEmpty()) {

                Picasso.with(itemViewHolder.ivOwnerPicture.getContext())
                        .load(pullRequest.getUser().getAvatarUrl())
                        .transform(new CircleTransform())
                        .fit().centerCrop()
                        .placeholder(R.drawable.ic_avatar)
                        .into(itemViewHolder.ivOwnerPicture);
            } else {
                itemViewHolder.ivOwnerPicture.setImageResource(R.drawable.ic_avatar);
            }

            itemViewHolder.tvPullRequestTitle.setText(pullRequest.getTitle());
            itemViewHolder.tvPullRequestBody.setText(pullRequest.getBody());
            itemViewHolder.tvOwnerLogin.setText(pullRequest.getUser().getLogin());
            itemViewHolder.tvOwnerFullname.setText(pullRequest.getUser().getLogin());
        } else {

            PullRequestsAdapter.HeaderViewHolder headerViewHolder = ((PullRequestsAdapter.HeaderViewHolder) holder);
            //headerViewHolder.tvTotalPullRequestsOpened.setText();
            //headerViewHolder.tvTotalPullRequestsClosed.setText();
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

        ConstraintLayout mLayout;

        TextView tvTotalPullRequestsOpened;
        TextView tvTotalPullRequestsClosed;

        HeaderViewHolder(View itemView) {
            super(itemView);
            mLayout = itemView.findViewById(R.id.cl_item_pullrequest_header);
            tvTotalPullRequestsOpened = itemView.findViewById(R.id.tv_total_pull_requests_opened);
            tvTotalPullRequestsClosed = itemView.findViewById(R.id.tv_total_pull_requests_closed);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemListListener<PullRequest> mItemListener;

        ConstraintLayout mLayout;
        TextView tvPullRequestTitle;
        TextView tvPullRequestBody;
        ImageView ivOwnerPicture;
        TextView tvOwnerLogin;
        TextView tvOwnerFullname;

        ItemViewHolder(View itemView, final ItemListListener<PullRequest> itemListener) {
            super(itemView);
            this.mItemListener = itemListener;

            mLayout = itemView.findViewById(R.id.cl_item_pullrequest);
            tvPullRequestTitle = itemView.findViewById(R.id.tv_pull_request_title);
            tvPullRequestBody = itemView.findViewById(R.id.tv_pull_request_body);
            ivOwnerPicture = itemView.findViewById(R.id.iv_owner_picture);
            tvOwnerLogin = itemView.findViewById(R.id.tv_owner_login);
            tvOwnerFullname = itemView.findViewById(R.id.tv_owner_fullname);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mItemListener.onClickedItem(getItem(getAdapterPosition()));
        }
    }
}
