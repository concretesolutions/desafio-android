package br.com.appdesafio.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.appdesafio.R;
import br.com.appdesafio.databinding.RowPullRequestBinding;


import br.com.appdesafio.model.pojo.PullRequest;
import br.com.appdesafio.view.adapter.viewholder.ListPullRequestViewHolder;

public class ListPullRequestAdapter extends RecyclerView.Adapter<ListPullRequestViewHolder> {
    private List<PullRequest> mItemList;
    private LayoutInflater layoutInflater;
    private Context mContext;

    public ListPullRequestAdapter(final Context context) {
        this.mContext = context;
    }

    public void setRepository(final List<PullRequest> items){
        this.mItemList = items;
        notifyDataSetChanged();
    }

    @Override
    public ListPullRequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        RowPullRequestBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.row_pull_request, parent, false);
        return new ListPullRequestViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ListPullRequestViewHolder holder, int position) {

        holder.binding.setPull(mItemList.get(position));

        final Drawable[] image = new Drawable[1];


        holder.binding.imgOwner.buildDrawingCache();
        Picasso
                .get()
                .load(mItemList.get(position).getUser().getAvatarUrl())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(holder.binding.imgOwner, new Callback() {
                    @Override
                    public void onSuccess() {
                        image[0] = holder.binding.imgOwner.getDrawable();
                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso
                                .get()
                                .load(mItemList.get(position).getUser().getAvatarUrl())
                                .into(holder.binding.imgOwner, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        image[0] = holder.binding.imgOwner.getDrawable();
                                    }

                                    @Override
                                    public void onError(Exception e) {

                                    }
                                });
                    }


                });
        holder.binding.rltItemRepository.setOnClickListener(view -> {
            String url = mItemList.get(position).getHtmlUrl();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            mContext.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }


}
