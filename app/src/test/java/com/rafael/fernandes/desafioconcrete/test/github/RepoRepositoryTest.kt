import android.content.Context
import androidx.room.Room
import com.rafael.fernandes.data.database.RepositoryDatabase
import com.rafael.fernandes.data.database.dao.RepositoryDao
import com.rafael.fernandes.data.network.RestApi
import com.rafael.fernandes.desafioconcrete.util.TestUtil
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class RepoRepositoryTest {

    private var mockWebServer = MockWebServer()
    private lateinit var apiService: RestApi

    @Before
    fun setup() {

        mockWebServer.start()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient())
            .build()
            .create(RestApi::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }


    @Test
    fun testPahtRepository() {
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
        enqueueResponse()

        mockWebServer.enqueue(response)


        // Act
        val repository = apiService.getOnlineRepositories("stars", 1).blockingGet()

        val request = mockWebServer.takeRequest()
        Assert.assertThat(request.path, CoreMatchers.`is`("search/repositories?q=language:Java&sort=stars&page=1"))

    }

    private fun enqueueResponse(headers: Map<String, String> = emptyMap()) {
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse
                .setBody(
                    "[{\"description\":\"Design patterns implemented in Java\",\"favorite\":true,\"forks_count\":16302,\"full_name\":\"iluwatar/java-design-patterns\",\"id\":22790488,\"name\":\"java-design-patterns\",\"owner\":{\"avatar_url\":\"https://avatars1.githubusercontent.com/u/582346?v\\u003d4\",\"events_url\":\"https://api.github.com/users/iluwatar/events{/privacy}\",\"followers_url\":\"https://api.github.com/users/iluwatar/followers\",\"following_url\":\"https://api.github.com/users/iluwatar/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/iluwatar/gists{/gist_id}\",\"gravatar_id\":\"\",\"html_url\":\"https://github.com/iluwatar\",\"id\":582346,\"login\":\"iluwatar\",\"organizations_url\":\"https://api.github.com/users/iluwatar/orgs\",\"received_events_url\":\"https://api.github.com/users/iluwatar/received_events\",\"repos_url\":\"https://api.github.com/users/iluwatar/repos\",\"site_admin\":false,\"starred_url\":\"https://api.github.com/users/iluwatar/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/iluwatar/subscriptions\",\"type\":\"User\",\"url\":\"https://api.github.com/users/iluwatar\"},\"stargazers_count\":50504}]"
                )
        )
    }
}