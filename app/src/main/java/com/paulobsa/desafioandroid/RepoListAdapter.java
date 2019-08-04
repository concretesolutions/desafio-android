package com.paulobsa.desafioandroid;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.paulobsa.desafioandroid.model.Item;
import com.paulobsa.desafioandroid.model.SearchResult;

public class RepoListAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private RepoListAdapterOnclickHandler mHandler;
    private Context mContext;
    private SearchResult searchResult;
    private static final int LOADING = 0;
    private static final int ITEM = 1;
    private boolean isLoaderVisible = false;

    public RepoListAdapter(RepoListAdapterOnclickHandler handler, Context context) {
        this.mHandler = handler;
        this.mContext = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item, parent, false));
            case LOADING:
                return new FooterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder baseViewHolder, final int position) {
        baseViewHolder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == searchResult.getItems().size() - 1 ? LOADING : ITEM;
        } else {
            return ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return searchResult == null
                || searchResult.getItems() == null ? 0 : searchResult.getItems().size();
    }

    public void addSearchResult(SearchResult searchResult) {
        if (this.searchResult == null) {
            this.searchResult = searchResult;
        } else {
            this.searchResult.getItems().addAll(searchResult.getItems());
        }
        isLoaderVisible = false;
        notifyDataSetChanged();
    }

    public SearchResult getSearchResult() {
        return searchResult;
    }

    public void addItem(Item item) {
        searchResult.getItems().add(item);
        notifyItemInserted(searchResult.getItems().size() - 1);
    }

    private void removeItem(Item item) {
        int position = searchResult.getItems().indexOf(item);
        if (position > -1) {
            searchResult.getItems().remove(position);
            notifyItemRemoved(position);
        }
    }

    public void addLoading() {
        isLoaderVisible = true;
        addItem(new Item());
    }

    public void removeLoading() {
        isLoaderVisible = false;
        if (searchResult != null && searchResult.getItems() != null) {
            int position = searchResult.getItems().size() - 1;
            Item item = getItem(position);
            if (item != null) {
                searchResult.getItems().remove(position);
                notifyItemRemoved(position);
            }
        }
    }

    public void clear() {
        searchResult = null;
    }

    Item getItem(int position) {
        return searchResult.getItems().get(position);
    }

    public class ViewHolder extends BaseViewHolder {
        CardView mCard;
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);

            this.mCard = itemView.findViewById(R.id.repo_info_card);
            this.textView = itemView.findViewById(R.id.textViewTitle);
        }

        @Override
        protected void clear() {

        }

        public void onBind(final int position) {
            super.onBind(position);
            Item item = searchResult.getItems().get(position);

            textView.setText(item.getName());
            mCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mHandler.onCardClick(searchResult.getItems().get(position).getName());
                }
            });
        }
    }

    public class FooterHolder extends BaseViewHolder {
        ProgressBar mProgressBar;

        FooterHolder(View itemView) {
            super(itemView);
            mProgressBar = itemView.findViewById(R.id.progressBar);
        }

        @Override
        protected void clear() {
        }

    }

    public interface RepoListAdapterOnclickHandler {
        void onCardClick(String repoJson);
    }

}
