package com.br.apigithub.view;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;
import android.widget.Toast;

import com.br.apigithub.R;
import com.br.apigithub.aac.RepositoryViewModel;
import com.br.apigithub.beans.GithubRepository;
import com.br.apigithub.beans.Issue;
import com.br.apigithub.beans.Pull;
import com.br.apigithub.fragments.IssueFragment;

import java.util.List;

/**
 * Created by rlima on 04/10/18.
 */

public class MainActivity extends AppCompatActivity {
    private List<GithubRepository> repository;
    private RepositoryViewModel repoViewModel;
    private String query;
    private String msgError;
    private ProgressDialog progressDialog;

    Observer<List<GithubRepository>> observerRepository = new Observer<List<GithubRepository>>() {
        @Override
        public void onChanged(@Nullable List<GithubRepository> githubRepositories) {
            repository = githubRepositories;
        }
    };

//    Observer<List<Pull>> observerPulls = new Observer<List<Pull>>() {
//        @Override
//        public void onChanged(@Nullable List<Pull> pulls) {
//            getProgressDialog().show();
//            FragmentManager fm = getSupportFragmentManager();
//            PullRequestFragment fragment = (PullRequestFragment) fm.getFragments().get(2);
//            fragment.setPulls(pulls);
//
//            if (repository.getPulls() == null) {
//                fragment.setAdapter();
//            } else {
//                fragment.updateAdapter();
//            }
//            repository.setPulls(pulls);
//        }
//    };

    Observer<String> observerMsgError = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) {
            msgError = s;
            getProgressDialog().dismiss();
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repoViewModel = ViewModelProviders.of(this).get(RepositoryViewModel.class);
        repoViewModel.getGithubLiveData().observe(this, observerRepository);
//        repoViewModel.getPullsLiveData().observe(this, observerPulls);
        repoViewModel.getMsgError().observe(this, observerMsgError);
        repoViewModel.init();
    }

    // FIXME esse método não é necessário no envio do projeto. Apagar antes da entrega.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.contains("/")) {
                    Toast.makeText(getApplicationContext(), "A busca deve seguir o padrão usuário/repositório", Toast.LENGTH_LONG).show();
                } else {
                    setQuery(query);
//                    repoViewModel.searchRepo(query, 1, 10);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public RepositoryViewModel getRepoViewModel() {
        return repoViewModel;
    }

    public void initProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getResources().getString(R.string.text_carregando));
    }

    public ProgressDialog getProgressDialog() {
        if (progressDialog == null) {
            initProgressDialog();
        }
        return progressDialog;
    }

    public void stopLoading() {
        if (getProgressDialog().isShowing()) {
            getProgressDialog().dismiss();
        }
    }
}
