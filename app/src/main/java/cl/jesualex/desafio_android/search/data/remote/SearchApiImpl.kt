package cl.jesualex.desafio_android.search.data.remote

import cl.jesualex.desafio_android.BuildConfig
import cl.jesualex.desafio_android.base.remote.ApiServiceFactory
import cl.jesualex.desafio_android.search.data.domain.entity.SearchBase
import io.reactivex.Observable

/**
 * Created by jesualex on 2019-05-28.
 */
class SearchApiImpl private constructor(): SearchApi {
    private val path = "/search/"
    private lateinit var api: SearchApi

    private fun getApiInstance(): SearchApi {
        if(!::api.isInitialized){
            api = ApiServiceFactory.build(SearchApi::class.java, BuildConfig.GH_BASE_URL + path)
        }

        return api
    }

    override fun search(lang: String, sortBy: String, page: Int): Observable<SearchBase> {
        return getApiInstance().search(lang, sortBy, page)
    }

    companion object{
        private lateinit var instance: SearchApiImpl

        fun getInstance(): SearchApiImpl{
            if(!::instance.isInitialized){
                instance = SearchApiImpl()
            }

            return instance
        }
    }
}