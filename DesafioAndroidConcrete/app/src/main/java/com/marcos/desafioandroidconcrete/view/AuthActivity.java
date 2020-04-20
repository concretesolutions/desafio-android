package com.marcos.desafioandroidconcrete.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.marcos.desafioandroidconcrete.BuildConfig;
import com.marcos.desafioandroidconcrete.R;
import com.marcos.desafioandroidconcrete.domain.AcessToken;
import com.marcos.desafioandroidconcrete.util.Methods;
import com.marcos.desafioandroidconcrete.viewmodel.GitHubRequestViewModel;

public class AuthActivity extends AppCompatActivity {

    public static final String GITHUB_AUTH_PATH = "https://github.com/login/oauth/authorize" + "?client_id=" + BuildConfig.CLIENT_ID + "&scope=repo&redirect_uri=" + BuildConfig.REDIRECT_URI;


    Activity activity;
    View contextView;
    String code = "";
    private Button btnAuth;
    private ProgressBar progressBar;
    GitHubRequestViewModel gitHubRequestViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        code = Methods.fetchCode(this);
        initialize();
        configViewModel();

        if (!code.contentEquals("")) {
            navigate();
        }
    }

    private void configViewModel() {
        gitHubRequestViewModel = ViewModelProviders.of(this).get(GitHubRequestViewModel.class);
        gitHubRequestViewModel.gitAcessTokenMutableLiveData.observe((LifecycleOwner) activity, new Observer<AcessToken>() {
            @Override
            public void onChanged(AcessToken acessToken) {
                showProgressBar(false);
                if (acessToken != null) {
                    Methods.saveAccount(activity, "Token " + acessToken.getAccessToken(), code);
                    showMessage(true);
                } else {
                    showMessage(false);
                }
            }
        });

    }

    private void showMessage(boolean success) {
        if (success) {
            Snackbar.make(contextView, R.string.sucess_auth, Snackbar.LENGTH_SHORT)
                    .show();

            Snackbar.make(contextView, R.string.sucess_auth, Snackbar.LENGTH_SHORT).addCallback(new Snackbar.Callback() {
                @Override
                public void onDismissed(Snackbar snackbar, int event) {
                    super.onDismissed(snackbar, event);
                    navigate();

                }
            }).show();

        } else {
            Snackbar.make(contextView, R.string.error, Snackbar.LENGTH_SHORT)
                    .show();
        }
    }

    private void initialize() {
        activity = this;
        contextView = findViewById(R.id.context_view);
        progressBar = findViewById(R.id.progressBar);
        btnAuth = findViewById(R.id.btn_auth);
        btnAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressBar(true);
                auth();
            }
        });
    }

    public void showProgressBar(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
            btnAuth.setEnabled(false);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            btnAuth.setEnabled(true);
        }
    }


    private void auth() {
        if (code.contentEquals("")) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(GITHUB_AUTH_PATH));
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(BuildConfig.REDIRECT_URI)) {
            code = uri.getQueryParameter("code");
            gitHubRequestViewModel.getAccessToken(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET, code);
        }
    }

    public void navigate() {
        startActivity(new Intent(activity, MainActivity.class));
    }
}
