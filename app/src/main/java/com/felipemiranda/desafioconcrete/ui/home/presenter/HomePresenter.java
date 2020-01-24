package com.felipemiranda.desafioconcrete.ui.home.presenter;

import com.felipemiranda.desafioconcrete.model.response.GenericErrorResponse;
import com.felipemiranda.desafioconcrete.model.response.SearchResponse;
import com.felipemiranda.desafioconcrete.network.ApiCreator;
import com.felipemiranda.desafioconcrete.network.RestCallback;
import com.felipemiranda.desafioconcrete.network.api.SearchApi;
import com.felipemiranda.desafioconcrete.ui.home.view.HomeView;
import com.felipemiranda.desafioconcrete.ui.main.BaseLoadingPresenter;

import org.jetbrains.annotations.NotNull;

import static com.felipemiranda.desafioconcrete.ui.main.PaginationListener.PAGE_START;

/**
 * Created by felipemiranda
 */

public class HomePresenter extends BaseLoadingPresenter<HomeView> {

    @Override
    public void bindView(HomeView view) {
        this.mView = view;
    }

    public void requestSearch(@NotNull Integer page) {
        addRequest(ApiCreator
                        .createService(SearchApi.class)
                        .requestSearch(page),

                new RestCallback<SearchResponse>() {
                    @Override
                    public void onSuccess(SearchResponse response) {
                        mView.successSearch(response);
                    }

                    @Override
                    public void onError(@NotNull GenericErrorResponse error) {
                        mView.notifyError(error);
                    }
                }, page == PAGE_START);
    }
}
