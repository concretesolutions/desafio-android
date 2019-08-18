package br.com.desafioandroid.views;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import br.com.desafioandroid.model.ListRepositories;
import br.com.desafioandroid.utils.ImageLoader;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.com.desafioandroid.R;
import br.com.desafioandroid.adapters.RepositoryAdapter;
import br.com.desafioandroid.model.Repository;
import br.com.desafioandroid.utils.DialogHoldon;
import br.com.desafioandroid.utils.Preferencias;
import br.com.desafioandroid.wsconsumer.RetrofitConfig;
import br.com.desafioandroid.wsconsumer.responses.ResponseRepositories;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    ListView listRepositories;
    List<Repository> repositories = new ArrayList<>();
    RepositoryAdapter adapter;
    ImageLoader imageLoader;//ImageLoader.getInstance();
    DialogHoldon dialogHoldon;
    Preferencias pref;
    int page = 1;
    //private Object obj List<Repository>;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle(getString(R.string.homeActivity));
        dialogHoldon = new DialogHoldon(this);
        dialogHoldon.setMessage(getString(R.string.buscandoRepos));

        pref = new Preferencias(this);

        listRepositories = (ListView) findViewById(R.id.listRepositories);

        int MyVersion = Build.VERSION.SDK_INT;
        if (MyVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (!checkIfAlreadyhavePermission()) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.ACCESS_NETWORK_STATE}, 101);

            }
        }

        listRepositories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (isOnline()) {
                    Intent intent = new Intent(HomeActivity.this, PullsActivity.class);
                    intent.putExtra("name", repositories.get(i).getOwner().getLogin());
                    intent.putExtra("repo", repositories.get(i).getName());
                    startActivity(intent);
                    finish();
                } else  {
                    Toast.makeText(HomeActivity.this, getString(R.string.offline),Toast.LENGTH_LONG).show();
                }
            }
        });

        listRepositories.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (absListView.getId() == listRepositories.getId()) {
                    if (listRepositories.getLastVisiblePosition() + 1 == repositories.size()) {
                        if (isOnline()) {
                            page++;
                            getRepo();
                        } else {
                            Toast.makeText(HomeActivity.this, getString(R.string.offline),Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });

        if (isOnline()) {
            getRepo();
        } else if (pref.verifyObje()) {
            Gson gson = new Gson();
            repositories = gson.fromJson(pref.getObjOffline(), ListRepositories.class).getRepositoryList();
            adapter = new RepositoryAdapter(repositories, HomeActivity.this);
            listRepositories.setAdapter(adapter);
            Toast.makeText(this, getString(R.string.offlineWithData), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, getString(R.string.offline), Toast.LENGTH_LONG).show();
        }

    }

    void getRepo() {
        dialogHoldon.showDialog();
        Call<ResponseRepositories> call = new RetrofitConfig().getService().getAllRepositories("language:Java","stars", page);
        call.enqueue(new Callback<ResponseRepositories>() {
            @Override
            public void onResponse(Call<ResponseRepositories> call, Response<ResponseRepositories> response) {
                ResponseRepositories resp = response.body();
                if (resp.getItems()!= null && resp.getItems().size()>0) {
                    repositories.addAll(resp.getItems());
                    if (page == 1) {
                        ListRepositories list = new ListRepositories(repositories);
                        pref.setObjOffline(list);
                        adapter = new RepositoryAdapter(repositories, HomeActivity.this);
                        listRepositories.setAdapter(adapter);
                    } else {
                        adapter.notifyDataSetChanged();
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialogHoldon.hideDialog();
                    }
                });

            }

            @Override
            public void onFailure(Call<ResponseRepositories> call, final Throwable t) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialogHoldon.hideDialog();
                        Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}