package com.accenture.githubrepositories.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.accenture.githubrepositories.R
import com.accenture.githubrepositories.adapters.PullRequestsAdapter
import com.accenture.githubrepositories.utils.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.pull_req_fragment_content.recycler_pull_req_frag_view
import android.support.v4.app.Fragment
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_main.*
import android.content.res.Configuration


class PRFragment : Fragment() {

    private lateinit var pullRequestsAdapter: PullRequestsAdapter
    private lateinit var progressBarPR : ProgressBar

    companion object {
        fun newInstance(repositoryAuthor: String?, repositoryName: String?): PRFragment {
            val args = Bundle()
            args.putString("repositoryAuthor", repositoryAuthor)
            args.putString("repositoryName", repositoryName)
            val fragment = PRFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.pull_req_fragment_main,container,false)
        val repositoryAuthor = arguments!!.getString("repositoryAuthor")
        val repositoryName = arguments!!.getString("repositoryName")
        val urlApiPulLReqForCall = "repos/$repositoryAuthor/$repositoryName/pulls"

        this.progressBarPR = view.findViewById(R.id.progressBarPR)
        this.progressBarPR.visibility = View.VISIBLE

            //INVOCAMOS EL API CON LA URL DINAMICA DE LOS PULL REQUESTS
            loadApiPullRequestData(urlApiPulLReqForCall)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pullRequestsAdapter = PullRequestsAdapter(context!!)
        //recycler_main_repos_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recycler_pull_req_frag_view.adapter = pullRequestsAdapter


    }


    @SuppressLint("CheckResult")
    fun loadApiPullRequestData(urlApiPullReq : String){

        try {

            val retrofitClient = RetrofitClient.create()

            retrofitClient.getPullRequests(urlApiPullReq)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ it ->
                        pullRequestsAdapter.setPullRequests(it.toList())
                        pullRequestsAdapter.notifyDataSetChanged()
                        this.progressBarPR.visibility = View.GONE
                        if(it.isEmpty()){

                            val activityView : View = activity!!.findViewById(R.id.contentMain)
                            activityView.visibility = View.VISIBLE
                            //REMOVE ALL FRAGMENTS INSTANCES AT THIS POINT
                            for (fragment in activity!!.supportFragmentManager.fragments) {
                                this.activity!!.supportFragmentManager.beginTransaction().remove(fragment).commit()
                            }

                            Toast.makeText(context, getString(R.string.errorNoPullRequests), Toast.LENGTH_SHORT).show()
                        }
                    },
                            {
                                Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                                Log.e(getString(R.string.generalError), it.message)
                                this.progressBarPR.visibility = View.GONE
                            })


        }catch (ile: IllegalStateException){throw ile}
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

    override fun onStart() {
        super.onStart()
        if (activity!!.contentMain != null) {
            activity!!.contentMain.visibility = View.INVISIBLE
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        if (activity!!.contentMain != null) {
            activity!!.contentMain.visibility = View.VISIBLE
            this.progressBarPR.visibility = View.INVISIBLE
        }
    }

}