package com.felipemiranda.desafioconcrete.ui.main;

import com.felipemiranda.desafioconcrete.model.response.GenericErrorResponse;

/**
 * Created by felipemiranda
 */

public interface BaseLoadingView extends FragmentViewBinder {

    void showLoading();

    void hideLoading();

    void notifyError(GenericErrorResponse error);

    void retryLastRequest();
}
