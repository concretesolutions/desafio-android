package com.br.apigithub.view;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;
import android.widget.Toast;

import com.br.apigithub.R;
import com.br.apigithub.aac.RepositoryViewModel;

/**
 * Created by rlima on 04/10/18.
 */

public class MainActivity extends AppCompatActivity implements RepositoryAdapter.ItemClickListener {
    public static final int INITIAL_PAGE = 1;
    private RepositoryViewModel repoViewModel;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repoViewModel = ViewModelProviders.of(this).get(RepositoryViewModel.class);

        if (savedInstanceState == null) {
            repoViewModel.listRepos(INITIAL_PAGE);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            RepositoryFragment fragment = RepositoryFragment.newInstance(repoViewModel);
            transaction.add(R.id.fragment_container, fragment, RepositoryFragment.TAG).commit();
        }
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

    @Override
    public void onItemClick(String ownerRepo, String repoName) {
        repoViewModel.listPullRequests(ownerRepo, repoName, INITIAL_PAGE);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        PullRequestFragment fragment = PullRequestFragment.newInstance();

        transaction.replace(R.id.fragment_container, fragment, PullRequestFragment.TAG).addToBackStack(PullRequestFragment.TAG).commit();
    }
}
