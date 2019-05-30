package cl.jesualex.desafio_android.repo.data.remote

import cl.jesualex.desafio_android.BuildConfig
import cl.jesualex.desafio_android.base.remote.ApiServiceFactory
import cl.jesualex.desafio_android.repo.data.domain.entity.Pull
import io.reactivex.Observable

/**
 * Created by jesualex on 2019-05-28.
 */

class RepoApiImpl private constructor(): RepoApi {
    private val path = "/repos/"
    private lateinit var api: RepoApi

    private fun getApiInstance(): RepoApi{
        if(!::api.isInitialized){
            api = ApiServiceFactory.build(RepoApi::class.java, BuildConfig.GH_BASE_URL + path)
        }

        return api
    }

    override fun getPulls(user: String, repo: String): Observable<List<Pull>> {
        return getApiInstance().getPulls(user, repo)
    }

    override fun getPulls(repoFull: String): Observable<List<Pull>> {
        return getApiInstance().getPulls(repoFull)
    }

    companion object{
        private lateinit var instance: RepoApiImpl

        fun getInstance(): RepoApiImpl{
            if(!::instance.isInitialized){
                instance = RepoApiImpl()
            }

            return instance
        }
    }
}