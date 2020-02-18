package br.com.bernardino.githubsearch.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.bernardino.githubsearch.model.Repository
import br.com.bernardino.githubsearch.model.RepositoryBody

@Dao
interface ReposDao {
    @Query("select * from repos")
    fun getRepositories() : LiveData<List<RepositoryDatabase>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(repositories : List<RepositoryDatabase>)
}

@Database(entities = [RepositoryDatabase::class], version = 1)
abstract class RepositoriesDatabase: RoomDatabase() {
    abstract val reposDao : ReposDao
}

private lateinit var INSTANCE: RepositoriesDatabase

fun getDatabase(context: Context): RepositoriesDatabase {
    synchronized(RepositoriesDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                RepositoriesDatabase::class.java,
                "repos").build()
        }
    }
    return INSTANCE
}
