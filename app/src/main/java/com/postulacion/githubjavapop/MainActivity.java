package com.postulacion.githubjavapop;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.postulacion.githubjavapop.adapter.RepositoryRecyclerViewAdapter;
import com.postulacion.githubjavapop.model.Repository;
import com.postulacion.githubjavapop.utils.GraphicDetailsKt;
import com.postulacion.githubjavapop.utils.Utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GraphicDetailsKt.changeColorInitialsViews(this, getWindow());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = findViewById(R.id.repositories);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        new MainActivity.GetRepositoriesAsync().execute(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class GetRepositoriesAsync extends AsyncTask<Context, Void, List<Repository>> {

        private String TAG = GetRepositoriesAsync.class.getSimpleName();
        private Context context;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i(TAG, "Initiation background task");
        }

        @Override
        protected List<Repository> doInBackground(Context... contexts) {
            context = contexts[0];
            Log.i(TAG, "start async task to get repositories");
            Utilities.getNickName();
            return getRepositories();
        }

        @Override
        protected void onPostExecute(List<Repository> repositories) {
            super.onPostExecute(repositories);

            if (repositories != null) {
                Log.i(TAG, "Task done");

                RepositoryRecyclerViewAdapter repositoryRecyclerViewAdapter = new RepositoryRecyclerViewAdapter(repositories, context);
                recyclerView.setAdapter(repositoryRecyclerViewAdapter);

                if (repositories.size() > 0) {
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                    recyclerView.startAnimation(animation);
                }
            }
        }
    }

    private List<Repository> getRepositories() {
        String Url = "https://api.github.com/search/repositories?q=language:Java&sort=stars&page=1";
        URL url = null;
        try {
            url = new URL(Url);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return Utilities.convertJsonToRepository(bufferedReader);
        } catch (Exception e) {
            Log.e("ERROR", "Detail: " + e);
        }
        return null;
    }
}
