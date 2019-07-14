package com.felipe.palma.desafioconcrete.ui.adapter;

import com.felipe.palma.desafioconcrete.domain.model.Item;

/**
 * Created by Felipe Palma on 12/07/2019.
 */
public interface RecyclerItemClickListener<T> {
    void onItemClick(T t);
}
