package com.gdavidpb.github.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.gdavidpb.github.R
import com.gdavidpb.github.utils.EXTRA_TITLE
import com.gdavidpb.github.utils.EXTRA_URL
import kotlinx.android.synthetic.main.activity_browser.*

class BrowserActivity : AppCompatActivity() {

    private val extraUrl by lazy {
        intent.getStringExtra(EXTRA_URL)
    }

    private val extraTitle by lazy {
        intent.getStringExtra(EXTRA_TITLE)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)

        supportActionBar?.title = getString(R.string.label_git_hub, extraTitle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        with(sWebView) {
            isEnabled = false

            isRefreshing = true
        }

        with(webView) {
            settings.javaScriptEnabled = true

            webChromeClient = WebChromeClient()

            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    sWebView.isRefreshing = false
                }
            }

            webView.loadUrl(extraUrl)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return true
    }
}
