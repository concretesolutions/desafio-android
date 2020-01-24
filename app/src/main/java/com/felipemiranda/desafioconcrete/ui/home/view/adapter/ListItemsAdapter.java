package com.felipemiranda.desafioconcrete.ui.home.view.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.felipemiranda.desafioconcrete.R;
import com.felipemiranda.desafioconcrete.model.Item;
import com.felipemiranda.desafioconcrete.ui.main.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by felipemiranda
 */

public class ListItemsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<Item> mItems;
    private OnClickItem mOnClickItem;
    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;

    public ListItemsAdapter(ArrayList<Item> mSearchResponses, OnClickItem onClickItem) {
        this.mItems = mSearchResponses;
        this.mOnClickItem = onClickItem;
    }

    public interface OnClickItem {
        void onClickItem(String url, String name);
    }

    @NotNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_LOADING) {
            return new ProgressHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false));
        }
        return new ItemViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false));

    }

    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == mItems.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    public void addItems(ArrayList<Item> postItems) {
        mItems.addAll(postItems);
        notifyDataSetChanged();
    }

    public void addLoading() {
        isLoaderVisible = true;
        mItems.add(new Item());
        notifyItemInserted(mItems.size() - 1);
    }
    public void removeLoading() {
        isLoaderVisible = false;
        int position = mItems.size() - 1;
        Item item = getItem(position);
        if (item != null) {
            mItems.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    private Item getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    class ItemViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_description)
        TextView tvDescription;
        @BindView(R.id.tv_fork)
        TextView tvFork;
        @BindView(R.id.tv_star)
        TextView tvStar;
        @BindView(R.id.iv_user)
        SimpleDraweeView ivUser;
        @BindView(R.id.tv_username)
        TextView tvUsername;
        @BindView(R.id.tv_user_fullname)
        TextView tvUserFullName;
        @BindView(R.id.item)
        LinearLayout linearItem;

        ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

        public void onBind(int position) {
            final Item item = mItems.get(position);

            linearItem.setOnClickListener(v -> mOnClickItem.onClickItem(item.getPulls_url(), item.getName()));

            tvName.setText(item.getName() != null ? item.getName() : "");
            tvDescription.setText(item.getDescription() != null ? item.getDescription() : "");
            tvFork.setText(item.getForks_count() != null ? String.valueOf(item.getForks_count()) : "0");
            tvStar.setText(item.getStargazers_count() != null ? String.valueOf(item.getStargazers_count()) : "0");
            tvUsername.setText(item.getOwner().getLogin() != null ? item.getOwner().getLogin() : "");
            tvUserFullName.setText(item.getFull_name() != null ? item.getFull_name() : "");

            if (mItems.get(position).getOwner().getAvatar_url() != null) {
                final int patternSizeImg = 420;
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(mItems.get(position).getOwner().getAvatar_url()))
                        .setResizeOptions(new ResizeOptions(patternSizeImg, patternSizeImg))
                        .build();
                ivUser.setController(
                        Fresco.newDraweeControllerBuilder()
                                .setOldController(ivUser.getController())
                                .setImageRequest(request)
                                .build());
            }
        }
    }

    public class ProgressHolder extends BaseViewHolder {
        ProgressHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }
    }
}
