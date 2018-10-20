package cl.mauledev.github.view.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import cl.mauledev.github.R
import cl.mauledev.github.utils.ConnectionUtils
import cl.mauledev.github.utils.Constants
import cl.mauledev.github.view.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_pr.*

class PullRequestFragment: Fragment() {

    private var viewModel: MainViewModel? = null

    private lateinit var url: String

    private lateinit var title: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState?.let {
            initExtras(it)
        } ?: run {
            initExtras(arguments)
        }

        initViewModel()
    }

    private fun initExtras(arguments: Bundle?) {
        title = arguments?.getString(Constants.PR_TITLE) ?: ""
        url = arguments?.getString(Constants.PR_URL) ?: ""
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pr, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebView()
    }

    private fun initWebView() {
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = WebViewClient()

        if (url.isNotEmpty() && ConnectionUtils.isConnected(requireContext()))
            webView.loadUrl(url)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireActivity() as AppCompatActivity
        val supportActionBar = activity.supportActionBar

        if (title.isNotEmpty())
            supportActionBar?.title = title

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        savedInstanceState?.let {
            webView.restoreState(it)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        webView.saveState(outState)
        outState.putString(Constants.REPO, title)
        outState.putString(Constants.PR_URL, url)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.getSelectedRepo()?.call()
    }
}