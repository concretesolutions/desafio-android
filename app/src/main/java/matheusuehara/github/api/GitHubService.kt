package matheusuehara.github.api

import matheusuehara.github.BuildConfig
import matheusuehara.github.contract.GitHubContract
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitHubService: GitHubContract.Service{

    var retrofit: Retrofit?  = null

    override fun getAPI():GitHubApi {
        if (retrofit == null) {
            retrofit = Retrofit
                    .Builder()
                    .baseUrl(BuildConfig.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit!!.create(GitHubApi::class.java)
    }
}