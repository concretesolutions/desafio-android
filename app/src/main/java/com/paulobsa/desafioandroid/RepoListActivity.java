package com.paulobsa.desafioandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paulobsa.desafioandroid.model.SearchResult;
import com.paulobsa.desafioandroid.model.Item;

public class RepoListActivity extends AppCompatActivity implements RepoListAdapter.RepoListAdapterOnclickHandler {

    private RequestQueue queue;
    private static final String LOG_TAG = "DESAFIO";
    private static final String url = "https://api.github.com/search/repositories?q=language:Java&sort=stars&page=";
    private RecyclerView mRecyclerView;
    private RepoListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Gson mGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_list);

        mRecyclerView = findViewById(R.id.repo_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RepoListAdapter(this, this);
        mRecyclerView.setAdapter(mAdapter);

        mGson = gson_builder();

        // Instantiate the RequestQueue
        queue = Volley.newRequestQueue(this);

        fetchRepoInfo(1);
    }

    private Gson gson_builder() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        return gsonBuilder.create();
    }

    private void fetchRepoInfo(Integer page) {
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + page,
                onResponse, onResponseError);

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private final Response.Listener<String> onResponse = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            // Display the first 500 characters of the response string.
            // textView.setText("Response is: "+ response.substring(0,500));
            Log.v(LOG_TAG, response);

            SearchResult searchResult = mGson.fromJson(response, SearchResult.class);
            setRepoList(searchResult);

            for (Item item : searchResult.getItems()) {
                Log.i(LOG_TAG, item.getName() + " " + item.getOwner().getUserName());
            }
        }
    };

    private final Response.ErrorListener onResponseError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            // textView.setText("That didn't work!");
            Log.v(LOG_TAG, error.toString());
        }
    };

    private void setRepoList(SearchResult searchResult) {
        mAdapter.setSearchResult(searchResult);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCardClick(String repoJson) {
        Toast.makeText(this, "Clicou!", Toast.LENGTH_LONG).show();
    }
}
