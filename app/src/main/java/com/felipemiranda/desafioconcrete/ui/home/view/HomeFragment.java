package com.felipemiranda.desafioconcrete.ui.home.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.felipemiranda.desafioconcrete.R;
import com.felipemiranda.desafioconcrete.model.response.SearchResponse;
import com.felipemiranda.desafioconcrete.ui.home.view.adapter.ListItemsAdapter;
import com.felipemiranda.desafioconcrete.ui.home.presenter.HomePresenter;
import com.felipemiranda.desafioconcrete.ui.item.view.ItemDetailFragment;
import com.felipemiranda.desafioconcrete.ui.main.BaseFragment;
import com.felipemiranda.desafioconcrete.ui.main.BaseLoadingPresenter;
import com.felipemiranda.desafioconcrete.ui.main.PaginationListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import butterknife.BindView;

import static com.felipemiranda.desafioconcrete.ui.main.PaginationListener.PAGE_START;

/**
 * Created by felipemiranda
 */

public class HomeFragment extends BaseFragment implements HomeView, ListItemsAdapter.OnClickItem {

    public static final String TAG = HomeFragment.class.getSimpleName();

    @BindView(R.id.rv_list_items)
    RecyclerView rvListItems;

    private HomePresenter mHomePresenter;
    private ListItemsAdapter listItemsAdapter;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private boolean isLoading = false;

    public static Fragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateViewBinder(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHomePresenter = new HomePresenter();
    }

    @Override
    public void onViewCreated(@NotNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(getString(R.string.github_java));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvListItems.setLayoutManager(linearLayoutManager);
        listItemsAdapter = new ListItemsAdapter(new ArrayList<>(), this);
        rvListItems.setAdapter(listItemsAdapter);

        mHomePresenter.requestSearch(currentPage);

        rvListItems.addOnScrollListener(new PaginationListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;
                mHomePresenter.requestSearch(currentPage);
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

    }

    @Override
    public BaseLoadingPresenter getPresenter() {
        return mHomePresenter;
    }

    @Override
    public void successSearch(final SearchResponse response) {
        if (response != null && response.getItems() != null && !response.getItems().isEmpty()) {
            if (currentPage != PAGE_START)
                listItemsAdapter.removeLoading();

            listItemsAdapter.addItems(response.getItems());

            if (currentPage < response.getTotalCount()) {
                listItemsAdapter.addLoading();
            } else {
                isLastPage = true;
            }
            isLoading = false;
        }
    }

    @Override
    public void onClickItem(@NotNull final String url, @NotNull final String name) {
        currentPage = PAGE_START;
        isLastPage = false;
        listItemsAdapter.clear();
        setFragmentContent(ItemDetailFragment.newInstance(url, name), TAG);
    }
}
