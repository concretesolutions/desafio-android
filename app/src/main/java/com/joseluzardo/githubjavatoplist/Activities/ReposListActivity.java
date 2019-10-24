package com.joseluzardo.githubjavatoplist.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.joseluzardo.githubjavatoplist.Adapters.ReposAdapter;
import com.joseluzardo.githubjavatoplist.Models.Repos.ItemRepos;
import com.joseluzardo.githubjavatoplist.Models.Repos.JavaList;
import com.joseluzardo.githubjavatoplist.Netwok.HttpService;
import com.joseluzardo.githubjavatoplist.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReposListActivity extends AppCompatActivity {

    private Context context;
    private RecyclerView rvJavaList;
    private RecyclerView.LayoutManager layoutManager;
    private int page = 1;
    private boolean load = false;
    private ProgressBar progressBar;
    private ReposAdapter reposAdapter;
    private List<ItemRepos> itemList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int total;
    private String typeLayout = "list";
    private String sort = "stars";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos_list);

        context = this;

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        swipeRefreshLayout = findViewById(R.id.srl);
        progressBar = findViewById(R.id.progress_bar);
        rvJavaList = findViewById(R.id.rvRepos);

        itemList = new ArrayList<>();
        layoutManager = new GridLayoutManager(context, 1);

        //change layout type
        final FloatingActionButton fab = findViewById(R.id.fabLayout);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (typeLayout.equals("list")){
                    layoutManager = new GridLayoutManager(context, 2);

                    typeLayout = "grid";
                    fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_list));
                    fab.hide();
                    fab.show();
                }else if (typeLayout.equals("grid")){
                    layoutManager = new GridLayoutManager(context, 1);

                    typeLayout = "list";
                    fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_grid));
                    fab.hide();
                    fab.show();
                }

                reposAdapter = new ReposAdapter(itemList, myClickListener,typeLayout);
                rvJavaList.setLayoutManager(layoutManager);
                rvJavaList.setItemAnimator(new DefaultItemAnimator());
                rvJavaList.setAdapter(reposAdapter);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getJavaList(1, false);
            }
        });

        NestedScrollView nsReposList = findViewById(R.id.nsReposList);

        //detect list end
        nsReposList.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                 @Override
                 public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                     if (!v.canScrollVertically(1)) {

                         if (!load && total>itemList.size()){

                             load = true;

                             Log.d("RV_SCROLL","FIN");

                             page++;

                             getJavaList(page, true);

                             progressBar.setVisibility(View.VISIBLE);

                         }
                     }
                 }
             });


        getJavaList(1, false);
    }

    //load list
    private void getJavaList(int page, final boolean act){

        if (!act){
            rvJavaList.setVisibility(View.INVISIBLE);
        }

        final Call<JavaList> javaList = HttpService.getRepos().list("language:Java",sort,String.valueOf(page));

        javaList.enqueue(new Callback<JavaList>() {
            @Override
            public void onResponse(@NonNull Call<JavaList> call, @NonNull Response<JavaList> response) {

                Log.d("REPO", new Gson().toJson(response.body()));

                if (response.body() != null) {

                    if (act){

                        itemList.addAll(response.body().getItems());
                        Log.d("listSize",String.valueOf(itemList.size()));

                        reposAdapter.notifyDataSetChanged();

                        load = false;

                        progressBar.setVisibility(View.GONE);

                    }else {

                        itemList.clear();
                        itemList.addAll(response.body().getItems());
                        Log.d("listSize",String.valueOf(itemList.size()));

                        if (itemList.size()==0){
                            dialog(getResources().getString(R.string.empty_list));
                            return;
                        }

                        total = response.body().getTotal_count();
                        Log.d("TOTAL",String.valueOf(total));

                        reposAdapter = new ReposAdapter(itemList, myClickListener,typeLayout);
                        rvJavaList.setVisibility(View.VISIBLE);
                        rvJavaList.setLayoutManager(layoutManager);
                        rvJavaList.setItemAnimator(new DefaultItemAnimator());
                        rvJavaList.setAdapter(reposAdapter);

                        swipeRefreshLayout.setRefreshing(false);
                    }

                }else {

                    dialog(getString(R.string.error_net));
                    swipeRefreshLayout.setRefreshing(false);
                    progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(@NonNull Call<JavaList> call, Throwable t) {

                dialog(getString(R.string.error_net));
                load = false;
                swipeRefreshLayout.setRefreshing(false);
                progressBar.setVisibility(View.GONE);
            }

        });
    }

    //click RecyclerView
    private View.OnClickListener myClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            int pos = Integer.valueOf(view.getTag().toString());

            Intent intent = new Intent(context,PullRequestActivity.class);
            intent.putExtra("user",itemList.get(pos).getOwner().getLogin());
            intent.putExtra("repo",itemList.get(pos).getName());

            startActivity(intent);

        }
    };

    //create options toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_repos, menu);
        return true;
    }

    //options toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itemStars:
                sort="stars";
                getJavaList(1, false);
                return true;
            case R.id.itemForks:
                sort="forks";
                getJavaList(1, false);
                return true;
            case R.id.itemUpdated:
                sort="updates";
                getJavaList(1, false);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

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
