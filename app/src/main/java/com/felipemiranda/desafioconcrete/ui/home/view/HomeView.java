package com.felipemiranda.desafioconcrete.ui.home.view;

import com.felipemiranda.desafioconcrete.model.response.SearchResponse;
import com.felipemiranda.desafioconcrete.ui.main.BaseLoadingView;

/**
 * Created by felipemiranda
 */

public interface HomeView extends BaseLoadingView {

    void successSearch(SearchResponse response);
}
