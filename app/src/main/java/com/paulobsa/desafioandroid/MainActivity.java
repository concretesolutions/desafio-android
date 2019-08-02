package com.paulobsa.desafioandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paulobsa.desafioandroid.data.SearchResult;
import com.paulobsa.desafioandroid.data.Item;

public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;
    private static final String LOG_TAG = "DESAFIO";
    private static final String url = "https://api.github.com/search/repositories?q=language:Java&sort=stars&page=";
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        // Instantiate the RequestQueue.
        queue = Volley.newRequestQueue(this);
        fetchRepoInfo(1);
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

            SearchResult searchResult = gson.fromJson(response, SearchResult.class);
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
}
