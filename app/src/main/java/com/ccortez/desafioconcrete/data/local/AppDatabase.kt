package com.ccortez.desafioconcrete.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ccortez.desafioconcrete.data.local.dao.ItemDao
import com.ccortez.desafioconcrete.data.local.dao.UserDao
import com.ccortez.desafioconcrete.model.ItemEntity
import com.ccortez.desafioconcrete.model.UserEntity

@Database(entities = [ItemEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    lateinit var INSTANCE: AppDatabase

    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDatabase::class.java, "github-item-list.db")
            .allowMainThreadQueries()
            .build()

    }

}