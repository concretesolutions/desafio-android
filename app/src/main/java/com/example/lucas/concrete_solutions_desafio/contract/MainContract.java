package com.example.lucas.concrete_solutions_desafio.contract;

import android.content.Context;

import com.example.lucas.concrete_solutions_desafio.model.Repository;
import com.example.lucas.concrete_solutions_desafio.model.RepositoryList;

public interface MainContract {

    interface View {
        void fillScreen (RepositoryList repositories);
        void refillScreen(RepositoryList repositories);
        void interruptProgressBar();
        void onRepositoryClicked(Repository repository);
    }

    interface Model{

    }

    interface Presenter {
         void repositoryRequisition (int pageNumber, final Context context);
         void fillScreenPresenter (RepositoryList repositories);
         void refillScreenPresenter(RepositoryList repositories);
         void interruptProgressBar();

    }

}
