package com.felipe.palma.desafioconcrete.ui.pullrequest;

import com.felipe.palma.desafioconcrete.domain.response.PullRequestResponse;

import java.util.List;

/**
 * Created by Felipe Palma on 13/07/2019.
 */
public interface PullRequestContract {
    interface View{
        void showPullRequests(List<PullRequestResponse> response);
        void showDialog();
        void hideDialog();
        void showError(String error);

    }

    interface Presenter{
        void loadPullRequests(String owner, String repo);
    }
}
