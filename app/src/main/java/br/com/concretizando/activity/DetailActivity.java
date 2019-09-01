package br.com.concretizando.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.concretizando.R;
import br.com.concretizando.adapter.ListPullRequestAdapter;
import br.com.concretizando.constant.ErrorMessage;
import br.com.concretizando.constant.ParameterIntentConstant;
import br.com.concretizando.dao.ApiGitHubDao;
import br.com.concretizando.model.PullRequest;
import br.com.concretizando.util.ChangePage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity implements Generic {

    private List<PullRequest> pullRequests;
    private TextView repositoryName;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        this.init();
    }

    private void init() {

        this.pullRequests = new ArrayList<>();
        this.repositoryName = findViewById(R.id.repositoryName);
        this.repositoryName.setText(getIntent().getStringExtra(ParameterIntentConstant.PARAM_2));
        this.list = findViewById(R.id.subList);
        this.loadPage();
        this.showToast(getResources().getString(R.string.str06));
    }

    private void initEventList() {

        this.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                openUrlPullRequest(i);
            }
        });
    }

    private void listAdd() {

        ListPullRequestAdapter adapter = new ListPullRequestAdapter(this.pullRequests, this);
        this.list.setAdapter(adapter);
        this.initEventList();
    }

    private void loadPage() {

        ApiGitHubDao dao = new ApiGitHubDao();
        dao.pullRequestsForRepo(getIntent().getStringExtra(ParameterIntentConstant.PARAM_1),
                getIntent().getStringExtra(ParameterIntentConstant.PARAM_2)).enqueue(new Callback<List<PullRequest>>() {
            @Override
            public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {

                pullRequests = response.body();
                listAdd();
            }

            @Override
            public void onFailure(Call<List<PullRequest>> call, Throwable t) {

                Log.e(ErrorMessage.TAG_1, ErrorMessage.ERROR_1, t);
            }
        });
    }

    private void openUrlPullRequest(int position) {

        ChangePage changePage = new ChangePage();
        changePage.openBrowserUrl(this,pullRequests.get(position).getHtml_url());
    }

    public void back(View view) {

        this.back();
    }

    public void back() {

        ChangePage changePage = new ChangePage();
        changePage.transferToMain(this);
    }

    @Override
    public void onBackPressed() {

        this.back();
    }

    @Override
    public void showToast(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
