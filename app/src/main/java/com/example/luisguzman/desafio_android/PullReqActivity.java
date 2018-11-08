package com.example.luisguzman.desafio_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.luisguzman.desafio_android.adapter.ApiAdapter;
import com.example.luisguzman.desafio_android.adapter.PullAdapter;
import com.example.luisguzman.desafio_android.modal.DataListPull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PullReqActivity extends AppCompatActivity {

    private static final String TAG = PullReqActivity.class.getSimpleName();


    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<DataListPull> pullList;
    private PullAdapter pullAdapter;

    String Owner;
    String Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_req);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            Owner = extras.getString("Owner");
            Name = extras.getString("Name");
        }

        progressBar = findViewById(R.id.progress_pull);
        recyclerView = findViewById(R.id.pullReq_list);
        pullList = new ArrayList<>();
        pullAdapter = new PullAdapter(pullList, this);

        getPullReqData(Owner, Name);
    }

    private void getPullReqData(String owner, String name) {
        progressBar.setVisibility(View.VISIBLE);

        Call<List<DataListPull>> call = ApiAdapter.getApiService().getPulls(owner, name);
        call.enqueue(new Callback<List<DataListPull>>() {
            @Override
            public void onResponse(Call<List<DataListPull>> call, Response<List<DataListPull>> response) {
                List<DataListPull> listPulls = response.body();
                System.out.println(listPulls);
                ArrayList<DataListPull> dataListPulls = new ArrayList<DataListPull>();
                for (int i = 0; i < listPulls.size(); i++) {
                    dataListPulls.add(listPulls.get(i));
                }
                addPulls(dataListPulls);
            }

            @Override
            public void onFailure(Call<List<DataListPull>> call, Throwable t) {
                Log.e(TAG, t.getLocalizedMessage());
                Toast.makeText(PullReqActivity.this, R.string.error_toast, Toast.LENGTH_LONG).show();
            }
        });


    }

    private void addPulls(ArrayList<DataListPull> pulls) {
        pullAdapter.notifyItemRangeInserted(pullList.size(), pulls.size());
        pullList.addAll(pulls);
        callRecyclerView(pullList);
        progressBar.setVisibility(View.GONE);
    }

    private void callRecyclerView(final List<DataListPull> pulls) {

        if (recyclerView.getAdapter() == null) {
            pullAdapter = new PullAdapter(pulls, this);
            linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(pullAdapter);
        }

    }
}
