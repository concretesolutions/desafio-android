package com.alexandreobs.testeconcrete.view.activitys;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alexandreobs.testeconcrete.R;
import com.alexandreobs.testeconcrete.model.pojo.pull.Request;
import com.alexandreobs.testeconcrete.view.adapter.RequestAdapter;
import com.alexandreobs.testeconcrete.view.interfaces.OnClickRequest;
import com.alexandreobs.testeconcrete.viewmodel.RequestViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.alexandreobs.testeconcrete.view.activitys.MainActivity.CREATOR_ID;
import static com.alexandreobs.testeconcrete.view.activitys.MainActivity.REPO_ID;

public class PullRequest extends AppCompatActivity implements OnClickRequest {

    private Request request;
    private List<Request> requestList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RequestAdapter adapter;
    private RequestViewModel viewModel;
    private ProgressBar progressBar;
    private  int pagina = 1;
    private  String creator;
    private  String repo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request);

        initViews();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String creator = bundle.getString(CREATOR_ID);
        String repo = bundle.getString(REPO_ID);


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        setScrollView();

        viewModel.getPullRequest(creator, repo, pagina);
        viewModel.getRequest().observe(this, requestList -> {
            adapter.atualizaLista(requestList);
        });

        progressBar = findViewById(R.id.progress_bar);
        viewModel.getLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loading) {
                if (loading) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });


    }


    public void initViews() {

        viewModel = ViewModelProviders.of(this).get(RequestViewModel.class);
        adapter = new RequestAdapter(requestList, this);
        recyclerView = findViewById(R.id.recyclerPullRequest);


    }


    @Override
    public void OnClick(Request request) {
        String url = request.getHtmlUrl();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void setScrollView(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                int totalItemCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastVisibleItemPosition();
                boolean ultimoItem = lastVisible + 5 >= totalItemCount;

                if (totalItemCount > 0 && ultimoItem){
                    pagina++;
                    viewModel.getPullRequest(creator, repo, pagina);
                }

            }
        });
    }
}
