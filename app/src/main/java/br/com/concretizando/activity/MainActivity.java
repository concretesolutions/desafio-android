package br.com.concretizando.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.Toast;

import br.com.concretizando.R;
import br.com.concretizando.adapter.ListRepositoryAdapter;
import br.com.concretizando.constant.ApiGitHubCredential;
import br.com.concretizando.constant.ErrorMessage;
import br.com.concretizando.dao.ApiGitHubDao;
import br.com.concretizando.model.ReposForStar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Generic {

    private ReposForStar repo;
    private ListRepositoryAdapter adapter;
    private LinearLayoutManager manager;
    private Boolean isScrolling;
    private int page, currentItems, totalItems, scrollOutItems;
    private RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();
    }

    private void init() {

        this.repo = new ReposForStar();
        this.list = findViewById(R.id.subList);
        this.isScrolling = false;
        this.page = 1;
        this.currentItems = 0;
        this.totalItems = 0;
        this.scrollOutItems = 0;
        this.initEventList();
        this.loadPage("1");
        this.showToast(getResources().getString(R.string.str06));
    }

    private void initEventList(){

        this.list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = manager.getChildCount();
                totalItems = manager.getItemCount();
                scrollOutItems = manager.findFirstVisibleItemPosition();

                if(isScrolling && (currentItems + scrollOutItems == totalItems))
                {
                    isScrolling = false;
                    page++;
                    loadPage(String.valueOf(page));
                    showToast(getResources().getString(R.string.str06));
                }
            }
        });
    }

    private void listAdd() {

        if(this.adapter == null) {
            this.adapter = new ListRepositoryAdapter(this.repo.getItems(), this, list);
            manager = new LinearLayoutManager(this);
            this.list.setLayoutManager(manager);
            this.list.setAdapter(adapter);
        } else {

            this.adapter.notifyDataSetChanged();
        }
    }

    private void loadPage(String page) {

        ApiGitHubDao dao = new ApiGitHubDao();
        dao.reposForStar(ApiGitHubCredential.ENPOINT_1_PARAM_1_VALUE,
                ApiGitHubCredential.ENPOINT_1_PARAM_2_VALUE, page).
            enqueue(new Callback<ReposForStar>() {

                @Override
                public void onResponse(Call<ReposForStar> call, Response<ReposForStar> response) {

                    repo.getItems().addAll(response.body().getItems());
                    listAdd();
                }

                @Override
                public void onFailure(Call<ReposForStar> call, Throwable t) {

                    Log.e(ErrorMessage.TAG_1, ErrorMessage.ERROR_1, t);
                }
            });
    }

    @Override
    public void showToast(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}