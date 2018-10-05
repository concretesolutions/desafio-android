package com.br.apigithub.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryVH> {
    @Override
    public RepositoryVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RepositoryVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class RepositoryVH extends RecyclerView.ViewHolder {

    public RepositoryVH(View itemView) {
        super(itemView);
    }
}
