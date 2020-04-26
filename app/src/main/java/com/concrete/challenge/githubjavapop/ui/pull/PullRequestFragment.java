package com.concrete.challenge.githubjavapop.ui.pull;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.test.espresso.IdlingResource;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.concrete.challenge.githubjavapop.R;

public class PullRequestFragment extends Fragment implements PullRequestRecyclerViewAdapter.ItemClickListener, IdlingResource {

    private PullRequestViewModel viewModel;

    private boolean isReady;
    private ResourceCallback resourceCallback;

    public static PullRequestFragment newInstance(Bundle bundle) {
        PullRequestFragment fragment = new PullRequestFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pull_request_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(PullRequestViewModel.class);

        Bundle bundle = getArguments();
        String user = bundle.getString("user");

        TextView textView = getView().findViewById(R.id.message);
        textView.setText(user);

        new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                isReady = true;
            }
        }.start();
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        if(isReady) resourceCallback.onTransitionToIdle();
        return isReady;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        resourceCallback = callback;
    }
}
