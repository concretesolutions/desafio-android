package cess.com.br.androidchallenge.UI.Activies;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cess.com.br.androidchallenge.R;
import cess.com.br.androidchallenge.UI.Presenters.PullRequestPresenter;
import cess.com.br.androidchallenge.UI.Views.PullRequestView;

public class PullRequestListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request_list);

        PullRequestView pullRequestView = new PullRequestView();

        Bundle args = new Bundle();
        args.putString("USER_NAME", getIntent().getStringExtra("USER_NAME"));
        args.putString("REPO_NAME", getIntent().getStringExtra("REPO_NAME"));

        if (savedInstanceState == null) {

            pullRequestView.setArguments(args);

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.fl_pr_fragment_holder, pullRequestView).commit();
            pullRequestView.setRetainInstance(true);
        }

        PullRequestPresenter mPresenter = new PullRequestPresenter(pullRequestView);
        pullRequestView.setPresenter(mPresenter);



    }
}
