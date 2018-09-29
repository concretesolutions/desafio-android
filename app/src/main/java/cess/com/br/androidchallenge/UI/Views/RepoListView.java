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

import cess.com.br.androidchallenge.Model.Remote.Repositories;
import cess.com.br.androidchallenge.R;
import cess.com.br.androidchallenge.UI.Adapters.RepoListAdapter;
import cess.com.br.androidchallenge.UI.Contracts.RepoContract;

public class RepoListView extends Fragment implements RepoContract.View {

    private RepoContract.Presenter mPresenter;
    private LinearLayoutManager linearLayoutManager;
    private RepoListAdapter repoListAdapter;
    private RecyclerView mRepoRecyclerView;

    private ImageView mTryAgain;
    private LinearLayout mErrorContainer;
    private TextView mErrorMessage;

    private ProgressBar mCenterLoader;
    private ProgressBar mFooterLoader;

    private int page_number = 1;

    private Boolean isLoading = true;
    private int pastVisibleItems, visibleItemCount, totalItemCount, previousTotal = 0;
    private int viewThreshold = 10;

    @Override
    public void setPresenter(RepoContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repo_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRepoRecyclerView = getView().findViewById(R.id.rv_repo_list);

        mCenterLoader = getView().findViewById(R.id.pb_centered);
        mFooterLoader = getView().findViewById(R.id.pb_footer);

        mErrorContainer = getView().findViewById(R.id.ll_error_container);
        mErrorMessage = getView().findViewById(R.id.tv_repo_error_message);

        mTryAgain = getActivity().findViewById(R.id.iv_try_again);

        mPresenter.getFirstPage("1","stars","language:Java");

        showCenterLoader();

    }

    @Override
    public void setupRecyclerView(RepoListAdapter repoListAdapter) {

        linearLayoutManager = new LinearLayoutManager(getContext());

        mRepoRecyclerView.setHasFixedSize(true);
        mRepoRecyclerView.setLayoutManager(linearLayoutManager);

        mRepoRecyclerView.addItemDecoration(
                new DividerItemDecoration(getContext(),
                        DividerItemDecoration.VERTICAL)
        );

        mRepoRecyclerView.setAdapter(repoListAdapter);

        mRepoRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();

                if (dy > 0) {
                    if (isLoading) {
                        if (totalItemCount > previousTotal) {
                            isLoading = false;
                            previousTotal = totalItemCount;
                            hideFooterLoader();
                        }
                    }

                    if(!isLoading && (totalItemCount - visibleItemCount) <= (pastVisibleItems + viewThreshold)) {
                        page_number++;
                        performPagination();
                        isLoading = true;
                    }
                }

            }
        });

    }

    @Override
    public void populateList(Repositories repositories) {

        showRepoList();

        repoListAdapter = new RepoListAdapter(repositories.getRepos(),getContext());
        setupRecyclerView(repoListAdapter);

        hideCenterLoader();
    }

    @Override
    public void loadMoreRepos(Repositories repositories) {

        if (repoListAdapter.addRepo(repositories.getRepos()))
            hideFooterLoader();
    }

    @Override
    public void showErrorMessage(int errorCode) {
        hideCenterLoader();
        hideFooterLoader();
        hideRepoList();

        mErrorContainer.setVisibility(View.VISIBLE);

        switch (errorCode) {
            case 1:
                mErrorMessage.setText(R.string.error_message_failure);
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
        showCenterLoader();
        mPresenter.getFirstPage("1","stars","language:Java");
    }

    @Override
    public void hideErrorMessage() {
        mErrorContainer.setVisibility(View.GONE);
    }

    @Override
    public void performPagination() {
        showFooterLoader();
        mPresenter.getNextPage(String.valueOf(page_number),"stars","language:Java");
    }

    @Override
    public void showCenterLoader() {
        mCenterLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideCenterLoader() {
        mCenterLoader.setVisibility(View.GONE);
    }

    @Override
    public void showFooterLoader() {
        mFooterLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideFooterLoader() {
        mFooterLoader.setVisibility(View.GONE);
    }

    @Override
    public void showRepoList() {
        mRepoRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRepoList() {
        mRepoRecyclerView.setVisibility(View.GONE);
    }
}
