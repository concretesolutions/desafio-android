package br.com.githubrepos.commons;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EndlessScrolling extends RecyclerView.OnScrollListener {

    private int currentPage = 1;
    private boolean loading = false;

    private RecyclerView.LayoutManager mLayoutManager;
    private OnLoadMoreListener mOnLoadMoreListener;

    public EndlessScrolling(RecyclerView.LayoutManager layoutManager, OnLoadMoreListener onLoadMoreListener) {
        this.mLayoutManager = layoutManager;
        this.mOnLoadMoreListener = onLoadMoreListener;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (mLayoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) mLayoutManager;

            int totalItemCount = gridLayoutManager.getItemCount();
            int lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
            int visibleThreshold = 3;
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