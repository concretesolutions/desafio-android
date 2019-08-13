package com.rafael.fernandes.desafioconcrete


import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import com.rafael.fernandes.data.database.RepositoryDatabase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


@RunWith(JUnit4::class)
class RepoRepositoryTest {


    private lateinit var dataBase: RepositoryDatabase

    @Before
    fun init() {

        dataBase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getContext(),
            RepositoryDatabase::class.java
        ).build()
    }

    @Test
    fun saveItem() {

        val testObserver = dataBase.respositoryDao.getAllRespositoriesSaved().test()
        testObserver.awaitTerminalEvent()
        testObserver
            .assertNoErrors()
            .assertValue { l -> !l.isEmpty() }

        /* var item = dao.getRepositoryById(1).blockingGet()

         var item2 = TestUtil.createRepo(
             REPOSITORY_ID,
             "owner",
             "repository_name",
             "repository_description"
         )

         var result = dao.insert(item)

         dao.getRepositoryById(1).test().as

         assert(result > 0)
 */
    }

    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(t: T?) {
                data[0] = t
                latch.countDown()
                liveData.removeObserver(this)//To change body of created functions use File | Settings | File Templates.
            }

        }
        liveData.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)

        return data[0] as T
    }

    /*@Test
    fun loadRepoFromNetwork() {

        val serv = service.getOnlineRepositories("", 1)


        val observer = mock<Observer<Resource<Item>>>()
        data.observeForever(observer)
        verifyNoMoreInteractions(service)
        verify(observer).onChanged(Resource.loading(null))
        val updatedDbData = MutableLiveData<Repo>()
        `when`(dao.load("foo", "bar")).thenReturn(updatedDbData)

        dbData.postValue(null)
        verify(service).getRepo("foo", "bar")
        verify(dao).insert(repo)

        updatedDbData.postValue(repo)
        verify(observer).onChanged(Resource.success(repo))

        // Preparation: mock DummyService
        val apiService = Mockito.mock(RestApi::class.java)
        Mockito.doReturn(Observable.just("one", "two")).`when`<RestApi>(apiService)
            .getOnlineRepositories("stars", 1).test()

        // Trigger

        // Validation
        apiService.a.assertValues("one", "two")
        // clean up
        apiService.dispose() // Pro-tip: don't forget this


        val apiResponse: ApiSuccessResponse<String> = ApiResponse
            .create<String>(Response.success("foo")) as ApiSuccessResponse<String>



        assertThat<String>(apiResponse.body, `is`("foo"))
        assertThat<Int>(apiResponse.nextPage, `is`(nullValue()))


        val dbData = MutableLiveData<Single<Item>>()
        `when`(dao.getRepositoryById(REPOSITORY_ID)).thenReturn(dbData.value)

        dbData.value

        var dd = ""

        *//* val repo = TestUtil.createRepo("foo", "bar", "desc")
         val call = successCall(repo)
         `when`(service.getOnlineRepositories("foo", 1)).thenReturn(call)

         val data = repository.loadRepo(REPOSITORY_ID)
         verify(dao).getRepositoryById("foo", "bar")
         verifyNoMoreInteractions(service)

         val observer = mock<Observer<Resource<Item>>>()
         data.observeForever(observer)
         verifyNoMoreInteractions(service)
         verify(observer).onChanged(Resource.loading(null))
         val updatedDbData = MutableLiveData<Repo>()
         `when`(dao.load("foo", "bar")).thenReturn(updatedDbData)

         dbData.postValue(null)
         verify(service).getRepo("foo", "bar")
         verify(dao).insert(repo)

         updatedDbData.postValue(repo)
         verify(observer).onChanged(Resource.success(repo))*//*
    }
*/
}