package com.example.brunovsiq.concreteapp.screens.pull_details;

import android.os.Bundle;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.brunovsiq.concreteapp.R;
import com.example.brunovsiq.concreteapp.models.Pull;
import com.example.brunovsiq.concreteapp.models.Repository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PullListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Repository repository;
    ArrayList<Pull> pullArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_list);
        recyclerView = findViewById(R.id.pull_list_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(PullListActivity.this));
        //findViewItems();

        repository = (Repository) getIntent().getSerializableExtra("repo");
        //https://api.github.com/repos/ + .username+/.repoName + /pulls

        getPulls();

    }

    private void getPulls() {

        AndroidNetworking.get("https://api.github.com/repos/{username}/{repoName}/pulls")
                .addPathParameter("username", repository.getUsername())
                .addPathParameter("repoName", repository.getRepoName())
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pullArrayList.clear();
                        for (int i=0;i<response.length();i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                // Pull(String pullTitle, String pullBody, String username, String fullname, String profilePictureUrl)
                                Pull pull = new Pull(jsonObject.getString("title"),
                                                     jsonObject.getString("body"),
                                                     jsonObject.getJSONObject("user").getString("login"),
                                                     jsonObject.has("name") ? jsonObject.getString("name") : "",
                                                     jsonObject.getJSONObject("user").getString("avatar_url"),
                                                     jsonObject.getString("url"));

                                pullArrayList.add(pull);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        PullAdapter pullAdapter = new PullAdapter(pullArrayList, PullListActivity.this);
                        if (recyclerView.getAdapter() == null) {
                            recyclerView.setAdapter(pullAdapter);
                        } else {
                            pullAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });


    }

}
