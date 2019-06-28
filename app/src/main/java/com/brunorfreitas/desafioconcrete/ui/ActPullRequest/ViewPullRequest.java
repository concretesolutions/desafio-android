package com.brunorfreitas.desafioconcrete.ui.ActPullRequest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.brunorfreitas.desafioconcrete.R;

public class ViewPullRequest extends AppCompatActivity {

    private Context context;
    //
    private Toolbar toolbar;
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pull_request);

        inicializaVariaves();
        inicializaAcoes();
    }

    private void inicializaVariaves() {
        context = getBaseContext();
        //
        webView = findViewById(R.id.act_pull_request_wb);
        toolbar = findViewById(R.id.act_pull_request_tb);
        //
        String url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        //
        webView.loadUrl(url);
        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void inicializaAcoes() {

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
