package com.felipemiranda.desafioconcrete.ui.item.view;

import com.felipemiranda.desafioconcrete.model.ItemDetail;
import com.felipemiranda.desafioconcrete.ui.main.BaseLoadingView;

import java.util.ArrayList;

/**
 * Created by felipemiranda
 */

public interface ItemDetailView extends BaseLoadingView {

    void successItemDetail(ArrayList<ItemDetail> response);
}
