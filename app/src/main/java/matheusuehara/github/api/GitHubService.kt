package matheusuehara.github.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface Service{
    fun getAPI(): GitHubApi
}

class GitHubService: Service{

    var retrofit: Retrofit?  = null

    override fun getAPI():GitHubApi {
        if (retrofit == null) {
            retrofit = Retrofit
                    .Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit!!.create(GitHubApi::class.java)
    }
}