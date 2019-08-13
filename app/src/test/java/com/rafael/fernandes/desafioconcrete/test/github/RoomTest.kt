package com.rafael.fernandes.desafioconcrete.test.github

import android.content.Context
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import com.rafael.fernandes.data.database.RepositoryDatabase
import com.rafael.fernandes.data.database.dao.RepositoryDao
import com.rafael.fernandes.desafioconcrete.util.TestUtil
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class RoomTest {

    lateinit var mMockContext: Context


    private lateinit var db: RepositoryDatabase

    private lateinit var dao: RepositoryDao

    @Before
    fun setup() {

        mMockContext = InstrumentationRegistry.getTargetContext()

        db = Room.inMemoryDatabaseBuilder(
            mMockContext, RepositoryDatabase::class.java
        ).build()

        dao = db.respositoryDao

    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun insertItem() {
        val item = TestUtil.createRepo(5, "owner", "nn", "des")
        dao.insert(item)

        val testObserver = dao.getRepositoryById(5).test()
        testObserver.awaitTerminalEvent()
        testObserver
            .assertNoErrors()
            .assertValue { l -> l != null }
    }
}
