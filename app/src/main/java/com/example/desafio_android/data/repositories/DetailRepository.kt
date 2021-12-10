package com.example.desafio_android.data.repositories

import com.example.desafio_android.data.model.repositoriesInfo
import com.example.desafio_android.data.pullmodel.RepositoryPullsItem
import com.example.desafio_android.data.repositories.source.RemoteApiSource
import com.example.desafio_android.viewmodel.AppResource
import retrofit2.Call
import java.lang.Exception
import javax.inject.Inject


class DetailRepository @Inject constructor(
    private val remoteApiSource: RemoteApiSource
){
    fun getRepositoryPulls(ownerName : String, repoName : String):AppResource<List<RepositoryPullsItem>?>{
        val call: Call<List<RepositoryPullsItem>> =
            remoteApiSource.getRepositoriesPull(ownerName, repoName)

        try{
            val response = call.execute()
            return if (response.isSuccessful) {
                val repositoryList = response.body()
                AppResource.Success(repositoryList)
            }else{
                AppResource.Error("Not Connected to the network")
            }

        } catch (e: Exception) {
            return AppResource.Error("Problems in the connection")
        }
    }
}