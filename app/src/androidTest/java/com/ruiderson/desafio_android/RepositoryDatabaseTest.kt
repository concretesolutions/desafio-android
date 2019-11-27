package com.ruiderson.desafio_android

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ruiderson.desafio_android.database.RepositoryDatabase
import com.ruiderson.desafio_android.models.Repository
import com.ruiderson.desafio_android.models.RepositoryCache
import com.ruiderson.desafio_android.models.RepositoryOwner
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepositoryDatabaseTest{

    private lateinit var database: RepositoryDatabase

    @Before
    fun init(){
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            RepositoryDatabase::class.java)
            .build()
    }

    @After
    fun closeDatabase(){
        database.close()
    }


    @Test
    fun insert_and_read_test(){
        val dao = database.repositoryDao()
        dao.deleteAll()
        var listCache = dao.getAll()
        assertEquals(listCache.count(), 0)

        val repository = Repository(50, "Unit Test", "Unit Test description", 10, 5, RepositoryOwner("fake url", "Test"))
        val cache = RepositoryCache(repository, 1)
        dao.insert(cache)
        listCache = dao.getAll()
        assertEquals(listCache.count(), 1)

        val restoredRepository = listCache.get(0).getRepository()
        assertEquals(repository.id, restoredRepository.id)
        assertEquals(repository.owner.login, restoredRepository.owner.login)
    }


}