package com.felipe.palma.desafioconcrete.ui.adapter;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Felipe Palma on 13/07/2019.
 */
public abstract class InfiniteScrollListener extends RecyclerView.OnScrollListener {

    private int minItemsBeforeNextLoad = 30;
    private int startingPage = 1;
    private int currentPage = 1;
    private int latestTotalItemCount = 0;
    private boolean isLoading = true;

    LinearLayoutManager layoutManager;

    public InfiniteScrollListener(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        minItemsBeforeNextLoad *= currentPage;

    }
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        int totalItemCount = layoutManager.getItemCount();


        // Assume list was invalidated -- set back to default
        if (totalItemCount < latestTotalItemCount) {
            this.currentPage = this.startingPage;
            this.latestTotalItemCount = totalItemCount;
        }

        // If still loading and dataset size has been updated,
        // update load state and last item count
        if (isLoading && totalItemCount > latestTotalItemCount) {
            isLoading = false;
            latestTotalItemCount = totalItemCount;
        }

        // If not loading and within threashold limit, increase current page and load more data
        if (!isLoading && (lastVisibleItemPosition + minItemsBeforeNextLoad > totalItemCount)) {
            currentPage++;
            onLoadMore(currentPage, totalItemCount, recyclerView);
            isLoading = true;
        }
    }

    public abstract void onLoadMore(int page, int totalItemsCount, RecyclerView view);
}