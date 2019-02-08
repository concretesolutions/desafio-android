package com.accenture.githubrepositories.fragments

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.accenture.githubrepositories.R
import kotlinx.android.synthetic.main.pull_req_fragment_detail_web_view.*
import kotlinx.android.synthetic.main.pull_req_fragment_detail_web_view.view.*


class PRFragmentWebView: Fragment(){

    companion object {
        fun newInstance(pullRequestUrl: String): PRFragmentWebView {
            val args = Bundle()
            args.putString("pullRequestUrl", pullRequestUrl)
            val fragment = PRFragmentWebView()
            fragment.arguments = args
            return fragment
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val storyUrl = arguments!!.getString("pullRequestUrl")
        val view = inflater.inflate(R.layout.pull_req_fragment_detail_web_view,container,false)

        val webView = view.webView

        val settings = webView.settings
        // Enable java script in web view
        settings.javaScriptEnabled = true

        // Enable and setup web view cache
        settings.setAppCacheEnabled(true)
        settings.cacheMode = WebSettings.LOAD_DEFAULT

        // Enable zooming in web view
        settings.setSupportZoom(true)
        settings.builtInZoomControls = true
        settings.displayZoomControls = true

        // Zoom web view text
        settings.textZoom = 125

        // Enable disable images in web view
        settings.blockNetworkImage = false
        // Whether the WebView should load image resources
        settings.loadsImagesAutomatically = true

        // More web view settings
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            settings.safeBrowsingEnabled = true  // api 26
        }

        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.mediaPlaybackRequiresUserGesture = false

        // More optional settings, you can enable it by yourself
        settings.domStorageEnabled = true
        settings.setSupportMultipleWindows(true)
        settings.loadWithOverviewMode = true
        settings.allowContentAccess = true
        settings.setGeolocationEnabled(false)
        settings.allowUniversalAccessFromFileURLs = true
        settings.allowFileAccess = true

        // WebView settings
        webView.fitsSystemWindows = true

        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null)

        // Set web view client
        webView.webViewClient = object: WebViewClient(){
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                // Enable disable back forward button
                //webView.canGoBack()
                webView.canGoForward()
            }

            override fun onPageFinished(view: WebView, url: String) {
                webView.canGoBack()
                //webView.canGoForward()
            }
        }

        // Set web view chrome client
        webView.webChromeClient = object: WebChromeClient(){
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                if(progressBar != null) progressBar.progress = newProgress
            }
        }

        webView.loadUrl(storyUrl)

        return view
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE || newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            try {
                val ft = fragmentManager!!.beginTransaction()
                ft.detach(this).attach(this).commit()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}