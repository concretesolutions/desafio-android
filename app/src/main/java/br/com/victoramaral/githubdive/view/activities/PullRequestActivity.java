package br.com.victoramaral.githubdive.view.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.victoramaral.githubdive.R;
import br.com.victoramaral.githubdive.model.pojos.requests.Request;
import br.com.victoramaral.githubdive.view.adapter.RequestAdapter;
import br.com.victoramaral.githubdive.viewmodel.RequestViewModel;

import static br.com.victoramaral.githubdive.view.activities.RepositoriesActivity.CREATOR_KEY;
import static br.com.victoramaral.githubdive.view.activities.RepositoriesActivity.REPOSITORY_KEY;

public class PullRequestActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestViewModel viewModel;
    private RequestAdapter adapter;
    private List<Request> requestsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request);
        initView();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            final String owner = bundle.getString(CREATOR_KEY);
            final String repository = bundle.getString(REPOSITORY_KEY);

            viewModel.getAllRequests(owner, repository);
            viewModel.getPullRequest().observe(this, request -> {
                if (request != null) {
                    adapter.setUpdate(request);
                } else {
                    Toast.makeText(this, "Houve um erro no carregamento dos dados",
                            Toast.LENGTH_LONG).show();
                    adapter.setUpdate(this.requestsList);
                }
            });
        }
    }

    public void initView() {
        recyclerView = findViewById(R.id.recyclerViewPull);
        viewModel = ViewModelProviders.of(this).get(RequestViewModel.class);
        adapter = new RequestAdapter(requestsList);
    }
}
