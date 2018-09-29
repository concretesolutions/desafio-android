package cess.com.br.androidchallenge.UI.Contracts;

import java.util.ArrayList;

import cess.com.br.androidchallenge.Model.Remote.PR;
import cess.com.br.androidchallenge.UI.BasePresenter;
import cess.com.br.androidchallenge.UI.BaseView;

public interface PullRequestContract {
    interface View extends BaseView<Presenter> {
        void populateList(ArrayList<PR> prArrayList);

        void showPrList();
        void hidePrList();

        void showPrLoader();
        void hidePrLoader();

        void setBackButtonAction();

        void showErrorMessage(int errorCode);
        void hideErrorMessage();

        void tryAgain();
    }

    interface Presenter extends BasePresenter {
        void getPullRequests(String userName, String repoName);
    }
}
