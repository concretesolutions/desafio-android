package com.anderson.apigithub_mvvm.feature.pullRequest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import br.com.anderson.apigithub_mvvm.ui.generic.base.viewmodel.BaseViewModel
import com.anderson.apigithub_mvvm.data.presentation.PullRequestPresentation
import com.anderson.apigithub_mvvm.service.PullRequestRepository
import javax.inject.Inject

/**
 * Created by anderson on 22/09/19.
 */
class PullRequestViewModel @Inject constructor(val repository: PullRequestRepository) : BaseViewModel(){

    val lisAux : MutableList<PullRequestPresentation> = arrayListOf()

    fun getListRepositoryLiveDate(creator : String, repositoryName: String): LiveData<List<PullRequestPresentation>> {

        return Transformations.map(repository.getPullsRequest(creator, repositoryName)){

            if(it != null){
                it.forEach { input ->
                    lisAux.add(
                        PullRequestPresentation(
                            input.user.login,
                            input.user.avatarUrl,
                            input.title,
                            formtDate(input.updatedDate),
                            input.body,
                            input.htmlUrl
                        )
                    )
                }

                lisAux
            }else{
                null
            }

            lisAux
        }
    }

    private fun formtDate(dateString: String): String{

        //LocalDate.parse(input.updatedDate, DateTimeFormatter.ISO_DATE).toString()

        return dateString
    }
}