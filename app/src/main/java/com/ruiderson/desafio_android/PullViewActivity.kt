package com.ruiderson.desafio_android

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.ruiderson.desafio_android.enum.IntentCode
import com.ruiderson.desafio_android.models.PullRequest

import kotlinx.android.synthetic.main.activity_pull_view.*

class PullViewActivity : AppCompatActivity() {


    private var pullRequest: PullRequest? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_view)
        setSupportActionBar(toolbar)


        //Back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener{
            onBackPressed()
        }


        //Get all datas from previous Activity
        pullRequest = (intent.getParcelableExtra(IntentCode.PULL_EXTRA.value) as PullRequest)


        //Update the toolbar title
        pullRequest?.let {
            supportActionBar?.title = pullRequest?.title
        }


        //Loads the WebView
        pullRequest?.let{
            val webView: WebView = findViewById(R.id.webView)
            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    view?.loadUrl(url)
                    return true
                }
            }
            webView.loadUrl(pullRequest?.html_url)
        }


    }




}