package br.com.victoramaral.githubdive.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import br.com.victoramaral.githubdive.R;
import br.com.victoramaral.githubdive.viewmodel.PrincipalViewModel;

public class PrincipalFragment extends Fragment {

    private PrincipalViewModel principalViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        principalViewModel =
                ViewModelProviders.of(this).get(PrincipalViewModel.class);
        View view = inflater.inflate(R.layout.fragment_principal, container, false);
        return view;
    }
}