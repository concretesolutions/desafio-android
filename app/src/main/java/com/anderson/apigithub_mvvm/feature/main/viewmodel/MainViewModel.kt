package com.anderson.apigithub_mvvm.feature.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import br.com.anderson.apigithub_mvvm.ui.generic.base.viewmodel.BaseViewModel
import com.anderson.apigithub_mvvm.data.presentation.RepositoryPresentation
import com.anderson.apigithub_mvvm.data.response.RepositoryResponse
import com.anderson.apigithub_mvvm.service.MainRepository
import javax.inject.Inject

/**
 * Created by anderson on 21/09/19.
 */
class MainViewModel @Inject constructor(val repository: MainRepository) : BaseViewModel() {

    val lisAux : MutableList<RepositoryPresentation> = arrayListOf()

    fun getListRepositoryLiveDate(page: Int): LiveData<List<RepositoryPresentation>> {

        return Transformations.map(repository.getListRepository(page)){

            var listRepositoryResponse: List<RepositoryResponse> = it.items

            if(listRepositoryResponse != null){
                listRepositoryResponse.forEach { input ->
                    lisAux.add(
                        RepositoryPresentation(
                            input.name,
                            input.ownerResponse.login,
                            input.starsCount.toString(),
                            input.forksCount.toString(),
                            input.pullsUrls,
                            input.ownerResponse.avatarUrl
                        ))
                }

                lisAux
            }else{
                null
            }
        }
    }
}