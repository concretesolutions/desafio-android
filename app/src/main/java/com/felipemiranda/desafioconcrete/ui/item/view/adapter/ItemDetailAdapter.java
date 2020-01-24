package com.felipemiranda.desafioconcrete.ui.item.view.adapter;

import android.annotation.SuppressLint;
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
import com.felipemiranda.desafioconcrete.model.ItemDetail;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by felipemiranda
 */

public class ItemDetailAdapter extends RecyclerView.Adapter<ItemDetailAdapter.AppViewHolder> {

    private ArrayList<ItemDetail> mItems;
    private OnClickItem mOnClickItem;

    public ItemDetailAdapter(ArrayList<ItemDetail> itemDetails, OnClickItem onClickItem) {
        this.mItems = itemDetails;
        this.mOnClickItem = onClickItem;
    }

    public interface OnClickItem {
        void onClickItem(String url);
    }

    @NotNull
    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AppViewHolder holder, int position) {
        final ItemDetail item = mItems.get(holder.getAdapterPosition());
        ItemViewHolder holderItem = (ItemViewHolder) holder;

        holderItem.item.setOnClickListener(v -> mOnClickItem.onClickItem(item.getHtml_url()));

        if(item.getCreated_at() != null) {
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            @SuppressLint("SimpleDateFormat") SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String formattedTime = "";

            try {
                Date date = sdf.parse(item.getCreated_at());
                formattedTime = output.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            holderItem.tvDate.setText(formattedTime);
        }
        holderItem.tvUsername.setText(item.getUser().getLogin() != null ? item.getUser().getLogin() : "");
        holderItem.tvName.setText(item.getTitle() != null ? item.getTitle() : "");
        holderItem.tvDescription.setText(item.getBody() != null ? item.getBody() : "");

        if (mItems.get(position).getUser().getAvatar_url() != null) {
            final int patternSizeImg = 420;
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(mItems.get(position).getUser().getAvatar_url()))
                    .setResizeOptions(new ResizeOptions(patternSizeImg, patternSizeImg))
                    .build();
            holderItem.ivUser.setController(
                    Fresco.newDraweeControllerBuilder()
                            .setOldController(holderItem.ivUser.getController())
                            .setImageRequest(request)
                            .build());
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class AppViewHolder extends RecyclerView.ViewHolder {
        AppViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ItemViewHolder extends AppViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_description)
        TextView tvDescription;
        @BindView(R.id.iv_user)
        SimpleDraweeView ivUser;
        @BindView(R.id.tv_username)
        TextView tvUsername;
        @BindView(R.id.item)
        LinearLayout item;

        ItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
