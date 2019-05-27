package com.losingtimeapps.javahub.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.losingtimeapps.javahub.ui.home.pullrequest.PullRequestFragment
import com.losingtimeapps.javahub.ui.home.repository.RepositoryFragment


class HomeActivityViewModel : ViewModel() {

    var tag: String = ""
    var title: String = ""

    var repositoryFragment: RepositoryFragment? = null
    var pullrequestFragment: PullRequestFragment? = null

    var liveData = MutableLiveData<String>()
    fun initHomeActivity() {
        liveData.value = tag
    }

}