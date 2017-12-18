package br.com.githubrepos.commons;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class EndlessScrolling extends RecyclerView.OnScrollListener {

    private int currentPage = 1;
    private boolean loading = false;

    private int visibleThreshold = 3;
    private int lastVisibleItem, totalItemCount;

    private RecyclerView.LayoutManager mLayoutManager;
    private OnLoadMoreListener mOnLoadMoreListener;

    public EndlessScrolling(RecyclerView.LayoutManager layoutManager, OnLoadMoreListener onLoadMoreListener) {
        this.mLayoutManager = layoutManager;
        this.mOnLoadMoreListener = onLoadMoreListener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (mLayoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) mLayoutManager;

            totalItemCount = gridLayoutManager.getItemCount();
            lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
            if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                //end has been reached, do something
                loading = true;
                currentPage++;
                this.mOnLoadMoreListener.onLoadMore(currentPage);
            }

        }
    }

    public void resetPagination() {
        this.currentPage = 1;
    }

    public void loaded() {
        this.loading = false;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public interface OnLoadMoreListener {
        void onLoadMore(int page);
    }
}