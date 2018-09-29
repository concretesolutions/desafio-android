package cess.com.br.androidchallenge.UI.Activies;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cess.com.br.androidchallenge.R;
import cess.com.br.androidchallenge.UI.Presenters.RepoPresenter;
import cess.com.br.androidchallenge.UI.Views.RepoListView;

public class RepoListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_list);

        RepoListView repoListView = new RepoListView();

        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.fl_repo_fragment_holder, repoListView).commit();
            repoListView.setRetainInstance(true);
        }

        RepoPresenter mPresenter = new RepoPresenter(repoListView);
        repoListView.setPresenter(mPresenter);


    }
}
