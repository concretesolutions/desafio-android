package com.felipe.palma.desafioconcrete.ui.repository;

import com.felipe.palma.desafioconcrete.domain.model.Item;

import java.util.List;

/**
 * Created by Felipe Palma on 11/07/2019.
 */
public interface RepositoriesContract {

    interface View {

        void hideDialog();
        void showDialog();
        void showError(String error);
        void showRepositories(List<Item> itens);
    }

    interface Presenter{
        void loadRepositories(int page);
    }

}
