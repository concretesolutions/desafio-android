package com.marcos.desafioandroidconcrete.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.marcos.desafioandroidconcrete.R;
import com.marcos.desafioandroidconcrete.adapter.PullRequestAdapter;
import com.marcos.desafioandroidconcrete.domain.PullRequest;
import com.marcos.desafioandroidconcrete.util.ItemClickListener;
import com.marcos.desafioandroidconcrete.util.Methods;
import com.marcos.desafioandroidconcrete.view.MainActivity;
import com.marcos.desafioandroidconcrete.viewmodel.PullRequestViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PullRequestFragment extends Fragment {

    public static final String PULL_REQUEST = "PULL_REQUEST";

    private String repositoryName = "";
    private String ownerName = "";
    private Activity activity;
    private View contextView;
    private List<PullRequest> pullRequest;

    private PullRequestAdapter adapter;
    RecyclerView recyclerview;
    LinearLayoutManager mLayoutManager;
    private View v;
    PullRequestViewModel pullRequestViewModel;
    private Fragment fragment;

    public PullRequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_pull_request, container, false);

        fragment = this;
        getBundle();
        initialize();
        configViewModel();
        validatePersistenceData();

        return v;

    }




    private void initialize() {
        activity = getActivity();
        activity.setTitle(repositoryName);
        contextView = v.findViewById(R.id.context_view);
        recyclerview = v.findViewById(R.id.recyclerview);
    }

    private void getBundle() {
        repositoryName = PullRequestFragmentArgs.fromBundle(getArguments()).getNameRepository();
        ownerName = PullRequestFragmentArgs.fromBundle(getArguments()).getNameOwner();
    }

    private void configViewModel() {
        pullRequestViewModel = ViewModelProviders.of(this).get(PullRequestViewModel.class);
        pullRequestViewModel.pullRequestMutableLiveData.observe(getViewLifecycleOwner(), new Observer<List<PullRequest>>() {
            @Override
            public void onChanged(List<PullRequest> newPullRequest) {
                if (newPullRequest != null) {
                    Methods.showProgress(((MainActivity) activity), false);
                    saveData(newPullRequest);
                }else{
                    Snackbar.make(contextView, R.string.error, Snackbar.LENGTH_SHORT)
                            .show();
                }
            }
        });

    }


    public void saveData(List<PullRequest> pullRequest) {
        if (pullRequest.size() > 0) {
            this.pullRequest = pullRequest;
            configRecycleview();
        }
    }

    private void configRecycleview() {
        adapter = new PullRequestAdapter(this.pullRequest);

        mLayoutManager = new LinearLayoutManager(activity);
        recyclerview.setHasFixedSize(true); //boa pratica
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerview.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerview.addItemDecoration(dividerItemDecoration);
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(pullRequest.get(position).getUrl()));
                startActivity(intent);
            }
        });
    }

    private void validatePersistenceData() {
        if (pullRequest == null && pullRequestViewModel.getPullRequestCache() == null) {
            Methods.showProgress(((MainActivity) activity), true);
            pullRequestViewModel.getPullRequest(Methods.fetchToken(activity),  ownerName, repositoryName);
        } else {
            pullRequest = pullRequestViewModel.getPullRequestCache();
        }
    }


}
