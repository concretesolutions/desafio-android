package cess.com.br.androidchallenge.UI.Views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;

import cess.com.br.androidchallenge.Model.Remote.PR;
import cess.com.br.androidchallenge.R;
import cess.com.br.androidchallenge.UI.Adapters.PullRequestListAdapter;
import cess.com.br.androidchallenge.UI.Adapters.RepoListAdapter;
import cess.com.br.androidchallenge.UI.Contracts.PullRequestContract;

public class PullRequestView extends Fragment implements PullRequestContract.View{

    private PullRequestContract.Presenter mPresenter;
    private RecyclerView mPRList;
    private ProgressBar mPrLoader;

    private ImageView mTryAgain;
    private LinearLayout mErrorContainer;
    private TextView mErrorMessage;

    private String user;
    private String repo;

    @Override
    public void setPresenter(PullRequestContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pull_request_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        user = getArguments().getString("USER_NAME");
        repo = getArguments().getString("REPO_NAME");

        setBackButtonAction();

        mErrorContainer = getView().findViewById(R.id.ll_error_container);
        mErrorMessage = getView().findViewById(R.id.tv_pr_error_message);

        mTryAgain = getActivity().findViewById(R.id.iv_try_again);

        mPrLoader = getView().findViewById(R.id.pb_pr_centered);
        mPRList = getView().findViewById(R.id.rv_pr_list);

        mPresenter.getPullRequests(user, repo);

        showPrLoader();
        hidePrList();

    }

    @Override
    public void showErrorMessage(int errorCode) {
        hidePrLoader();
        hidePrList();

        mErrorContainer.setVisibility(View.VISIBLE);

        switch (errorCode) {
            case 1:
                mErrorMessage.setText(R.string.error_message_failure);
                break;
            case 2:
                mErrorMessage.setText(R.string.error_message_no_prs);
                mTryAgain.setVisibility(View.INVISIBLE);
                break;
            default:
                mErrorMessage.setText(R.string.error_message);
        }

        mTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryAgain();
            }
        });
    }

    @Override
    public void tryAgain() {

        hideErrorMessage();

        mPresenter.getPullRequests(user, repo);

        showPrLoader();
        hidePrList();
    }

    @Override
    public void hideErrorMessage() {
        mErrorContainer.setVisibility(View.GONE);
    }

    @Override
    public void populateList(ArrayList<PR> prArrayList) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mPRList.setHasFixedSize(true);
        mPRList.setLayoutManager(linearLayoutManager);
        mPRList.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));

        PullRequestListAdapter pullRequestListAdapter = new PullRequestListAdapter(prArrayList, getContext());

        mPRList.setAdapter(pullRequestListAdapter);

        showPrList();
        hidePrLoader();
    }

    @Override
    public void showPrList() {
        mPRList.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePrList() {
        mPRList.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showPrLoader() {
        mPrLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePrLoader() {
        mPrLoader.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setBackButtonAction() {
        Toolbar tbToolbar = getView().findViewById(R.id.toolbar);
        tbToolbar.setTitle(R.string.app_name);

        tbToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finishAfterTransition();
                mPresenter.destroy();
            }
        });
    }

}
