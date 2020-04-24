package com.concrete.challenge.githubjavapop.ui.repository;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.concrete.challenge.githubjavapop.R;

public class RepositoryFragment extends Fragment {

    private RepositoryViewModel mViewModel;

    public static RepositoryFragment newInstance() {
        return new RepositoryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.repository_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RepositoryViewModel.class);
        // TODO: Use the ViewModel
    }

}
