package br.com.desafioandroid.views;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import br.com.desafioandroid.R;
import br.com.desafioandroid.adapters.PullsAdapter;
import br.com.desafioandroid.model.PullsRequests;
import br.com.desafioandroid.utils.DialogHoldon;
import br.com.desafioandroid.wsconsumer.RetrofitConfig;
import br.com.desafioandroid.wsconsumer.responses.ResponsePulls;
import br.com.desafioandroid.wsconsumer.responses.ResponseRepositories;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PullsActivity extends AppCompatActivity {

    String repo, name;
    RelativeLayout emptyPulls;
    DialogHoldon dialogHoldon;
    ListView listPulls;
    List<PullsRequests> pulls = new ArrayList<PullsRequests>();
    PullsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulls);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dialogHoldon = new DialogHoldon(this);
        dialogHoldon.setMessage(getString(R.string.buscandoPulls));

        emptyPulls = (RelativeLayout) findViewById(R.id.emptyPulls);
        emptyPulls.setVisibility(View.GONE);
        listPulls = (ListView) findViewById(R.id.listPulls);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            name = extras.getString("name");
            repo = extras.getString("repo");
        }

        setTitle(repo);

        listPulls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(pulls.get(i).getUrl()));
                startActivity(intent);
            }
        });

        getPulls();

    }

    void getPulls() {
        dialogHoldon.showDialog();
        Call<List<PullsRequests>> call = new RetrofitConfig().getService().getPulls(name, repo);

        call.enqueue(new Callback<List<PullsRequests>>() {
             @Override
             public void onResponse(Call<List<PullsRequests>> call, Response<List<PullsRequests>> response) {

                 pulls = response.body();
                 Gson gson = new Gson();


                 Log.e("TESTE > ", gson.toJson(pulls));
                 if (pulls.size()>0) {
                     emptyPulls.setVisibility(View.GONE);
                     adapter = new PullsAdapter(pulls, PullsActivity.this, name + "/" + repo);
                     listPulls.setAdapter(adapter);
                 } else {
                     emptyPulls.setVisibility(View.VISIBLE);

                 }
                 runOnUiThread(new Runnable() {
                     @Override
                     public void run() {
                         dialogHoldon.hideDialog();
                     }
                 });
             }

             @Override
             public void onFailure(Call<List<PullsRequests>> call, final Throwable t) {
                 runOnUiThread(new Runnable() {
                     @Override
                     public void run() {
                         dialogHoldon.hideDialog();
                         Toast.makeText(PullsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                     }
                 });
             }
         });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
