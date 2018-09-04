package com.example.rafaelanastacioalves.moby.pulllisting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.rafaelanastacioalves.moby.R;
import com.example.rafaelanastacioalves.moby.vo.Pull;
import com.example.rafaelanastacioalves.moby.listeners.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

class PullsListAdapter extends RecyclerView.Adapter<PullViewHolder> {
    private final Context mContext;
    private RecyclerViewClickListener recyclerViewClickListener;
    private List<Pull> items = new ArrayList<>();
    private RecyclerViewClickListener clickListener;

    public PullsListAdapter(Context context) {
        mContext = context;
    }

    private List<Pull> getItems() {
        return this.items;
    }

    public void setItems(List<Pull> data) {
        this.items = data;
        notifyDataSetChanged();
    }

    public void setRecyclerViewClickListener(RecyclerViewClickListener aRVC) {
        this.recyclerViewClickListener = aRVC;
    }

    @Override
    public PullViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PullViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pull_viewholder, parent, false), recyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(PullViewHolder holder, int position) {
        Pull aPull = getItems().get(position);
        (holder).bind(aPull, mContext);
    }

    @Override
    public int getItemCount() {
        if (getItems() != null){
            return this.items.size();

        }else{
            return 0;
        }
    }
}