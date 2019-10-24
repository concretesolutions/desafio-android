package com.joseluzardo.githubjavatoplist.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.gson.Gson;
import com.joseluzardo.githubjavatoplist.Adapters.PullAdapter;
import com.joseluzardo.githubjavatoplist.Models.Pulls.PullsItem;
import com.joseluzardo.githubjavatoplist.Netwok.HttpService;
import com.joseluzardo.githubjavatoplist.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class PullRequestActivity extends AppCompatActivity {

    private Context context;
    private List<PullsItem> itemList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private PullAdapter pullAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView rvPullList;
    private String user;
    private String repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request);

        context= this;

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        swipeRefreshLayout = findViewById(R.id.srl);
        rvPullList = findViewById(R.id.rvPullList);

        itemList = new ArrayList<>();
        layoutManager = new GridLayoutManager(context, 1);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPRs(user, repo);
            }
        });

        // params repo
        user = getIntent().getExtras().getString("user");
        repo = getIntent().getExtras().getString("repo");

        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(repo);

        //load list
        getPRs(user, repo);
    }

    private void getPRs(String user, String repo){

        rvPullList.setVisibility(INVISIBLE);

        final Call<List<PullsItem>> pullList = HttpService.getPullRequest().list(user,repo);

        pullList.enqueue(new Callback<List<PullsItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<PullsItem>> call, @NonNull Response<List<PullsItem>> response) {

                Log.d("REPO", new Gson().toJson(response.body()));

                if (response.body() != null) {

                    itemList.clear();
                    itemList.addAll(response.body());
                    Log.d("listSize",String.valueOf(itemList.size()));

                    if (itemList.size()==0){
                        dialog(getResources().getString(R.string.empty_list));
                        return;
                    }

                    pullAdapter = new PullAdapter(itemList, myClickListener);
                    rvPullList.setVisibility(VISIBLE);
                    rvPullList.setLayoutManager(layoutManager);
                    rvPullList.setItemAnimator(new DefaultItemAnimator());
                    rvPullList.setAdapter(pullAdapter);

                    swipeRefreshLayout.setRefreshing(false);

                }else {

                    dialog(getString(R.string.error_net));
                    swipeRefreshLayout.setRefreshing(false);
                }

            }

            @Override
            public void onFailure(Call<List<PullsItem>> call, Throwable t) {

                dialog(getString(R.string.error_net));
                Log.d("ERROR", t.getMessage());
                swipeRefreshLayout.setRefreshing(false);
            }


        });

    }

    //click RecyclerView
    private View.OnClickListener myClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            int pos = Integer.valueOf(view.getTag().toString());

            String url = itemList.get(pos).getHtml_url();

            Intent openUrlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            if (openUrlIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(openUrlIntent);
            }

        }
    };

    //global dialog
    private void dialog(String msj){

        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(msj);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }

}
