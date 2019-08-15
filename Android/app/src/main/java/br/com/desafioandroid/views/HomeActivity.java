package br.com.desafioandroid.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import br.com.desafioandroid.R;
import br.com.desafioandroid.adapters.RepositoryAdapter;
import br.com.desafioandroid.model.Repository;
import br.com.desafioandroid.wsconsumer.RetrofitConfig;
import br.com.desafioandroid.wsconsumer.responses.ResponseRepositories;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    ListView listRepositories;
    List<Repository> repositories = new ArrayList<>();
    RepositoryAdapter adapter;
    ImageLoader imageLoader = ImageLoader.getInstance();
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Github JavaPop");

        listRepositories = (ListView) findViewById(R.id.listRepositories);
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));

        getRepo();

        listRepositories.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (absListView.getId() == listRepositories.getId()) {
                    if (listRepositories.getLastVisiblePosition() + 1 == repositories.size()) {
                        page++;
                        getRepo();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });

    }

    void getRepo() {
        Call<ResponseRepositories> call = new RetrofitConfig().getService().getAllRepositories("language","stars", page);
        call.enqueue(new Callback<ResponseRepositories>() {
            @Override
            public void onResponse(Call<ResponseRepositories> call, Response<ResponseRepositories> response) {
                ResponseRepositories resp = response.body();
                if (resp.getItems()!= null && resp.getItems().size()>0) {
                    repositories.addAll(resp.getItems());
                    if (page == 1) {
                        adapter = new RepositoryAdapter(repositories, HomeActivity.this, imageLoader);
                        listRepositories.setAdapter(adapter);
                    } else {
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRepositories> call, Throwable t) {
                Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}