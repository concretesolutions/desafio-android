package com.example.lucas.concrete_solutions_desafio.contract;


import android.content.Context;
import com.example.lucas.concrete_solutions_desafio.model.PullRequestList;

public interface PullRequestContract {

    interface View {
        void fillScreen(PullRequestList pullRequests);
        void interruptProgressBar();
    }

    interface Model{

    }

    interface Presenter {
        void pullRequestRequisition (String full_name, final Context context);
        void fillScreenPresenter (PullRequestList pullRequests);
        void interruptProgressBar();
    }

}
