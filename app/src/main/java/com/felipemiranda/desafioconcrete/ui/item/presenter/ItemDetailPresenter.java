package com.felipemiranda.desafioconcrete.ui.item.presenter;

import com.felipemiranda.desafioconcrete.model.ItemDetail;
import com.felipemiranda.desafioconcrete.model.response.GenericErrorResponse;
import com.felipemiranda.desafioconcrete.network.ApiCreator;
import com.felipemiranda.desafioconcrete.network.RestCallback;
import com.felipemiranda.desafioconcrete.network.api.SearchApi;
import com.felipemiranda.desafioconcrete.ui.item.view.ItemDetailView;
import com.felipemiranda.desafioconcrete.ui.main.BaseLoadingPresenter;

import java.util.ArrayList;

/**
 * Created by felipemiranda
 */

public class ItemDetailPresenter extends BaseLoadingPresenter<ItemDetailView> {

    @Override
    public void bindView(ItemDetailView view) {
        this.mView = view;
    }

    public void requestItemDetail(String url) {
        addRequest(ApiCreator
                        .createService(SearchApi.class)
                        .requestItemDetail(url),

                new RestCallback<ArrayList<ItemDetail>>() {
                    @Override
                    public void onSuccess(ArrayList<ItemDetail> response) {
                        mView.successItemDetail(response);
                    }

                    @Override
                    public void onError(GenericErrorResponse error) {
                        mView.notifyError(error);
                    }
                }, true);
    }
}
