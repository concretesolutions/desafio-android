package br.com.appdesafio.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.appdesafio.R;
import br.com.appdesafio.databinding.RowRepositoryBinding;
import br.com.appdesafio.model.pojo.Item;

import br.com.appdesafio.util.IntentActions;
import br.com.appdesafio.util.OpenScreenUtil;
import br.com.appdesafio.view.adapter.viewholder.ListRepositoryViewHolder;


public class ListRepositoryAdapter extends RecyclerView.Adapter<ListRepositoryViewHolder> {
    private List<Item> mItemList;
    private LayoutInflater layoutInflater;
    private Context mContext;

    public ListRepositoryAdapter(final Context context) {
        this.mContext = context;
    }

    public void setRepository(final List<Item> items) {
        this.mItemList = items;
        notifyDataSetChanged();
    }

    @Override
    public ListRepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        RowRepositoryBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.row_repository, parent, false);
        return new ListRepositoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ListRepositoryViewHolder holder, int position) {

        holder.binding.setItem(mItemList.get(position));
        holder.binding.imgOwner.buildDrawingCache();
        Picasso
                .get()
                .load(mItemList.get(position).getOwner().getAvatarUrl())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(holder.binding.imgOwner, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso
                                .get()
                                .load(mItemList.get(position).getOwner().getAvatarUrl())
                                .into(holder.binding.imgOwner, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                    }

                                    @Override
                                    public void onError(Exception e) {

                                    }
                                });
                    }


                });
        holder.binding.rltItemRepository.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("creator", mItemList.get(position).getOwner().getLogin());
            bundle.putSerializable("repository", mItemList.get(position).getName());
            OpenScreenUtil.openScreen(mContext,
                    IntentActions.LIST_PULL_REQUEST_ACTIVITY.getAction(),
                    bundle, false);
        });
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

}