package br.com.andreyneto.desafioandroid.service

class ApiService {
    private val BASE_URL = "https://api.github.com/"

    fun getApi(): GitHubApi {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(GitHubApi::class.java)
    }

    interface GitHubApi {

    }
}