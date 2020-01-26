package br.com.victoramaral.githubdive.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import br.com.victoramaral.githubdive.R;
import br.com.victoramaral.githubdive.viewmodel.SobreViewModel;

public class SobreFragment extends Fragment {

    private SobreViewModel sobreViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sobreViewModel =
                ViewModelProviders.of(this).get(SobreViewModel.class);
        View view = inflater.inflate(R.layout.fragment_sobre, container, false);
        return view;
    }
}