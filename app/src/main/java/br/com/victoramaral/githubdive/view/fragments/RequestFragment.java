package br.com.victoramaral.githubdive.view.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.victoramaral.githubdive.R;
import br.com.victoramaral.githubdive.model.pojos.requests.Request;
import br.com.victoramaral.githubdive.view.adapter.RequestAdapter;
import br.com.victoramaral.githubdive.view.interfaces.RequestOnClick;
import br.com.victoramaral.githubdive.viewmodel.RequestViewModel;

import static br.com.victoramaral.githubdive.view.fragments.PrincipalFragment.CREATOR_KEY;
import static br.com.victoramaral.githubdive.view.fragments.PrincipalFragment.REPOSITORY_KEY;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestFragment extends Fragment {

    private RequestViewModel viewModel;
    private RecyclerView recyclerView;
    private RequestAdapter adapter;
    private List<Request> requestList = new ArrayList<>();

    public RequestFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewModelProviders.of(this).get(RequestViewModel.class);
        View view = inflater.inflate(R.layout.fragment_principal, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewRepositories);
        viewModel = ViewModelProviders.of(this).get(RequestViewModel.class);
        adapter = new RequestAdapter(requestList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        if (getArguments() != null) {
            String creator = getArguments().getString(CREATOR_KEY);
            String repository = getArguments().getString(REPOSITORY_KEY);

            viewModel.getAllRequests(creator, repository);
            viewModel.getPullRequest().observe(this, request -> {
                if (request != null) {
                    adapter.setUpdate(requestList);
                } else {
                    Toast.makeText(getContext(), "Houve um erro no carregamento dos dados",
                            Toast.LENGTH_SHORT).show();
                    adapter.setUpdate(this.requestList);
                }
            });
        }

        return view;
    }
}
