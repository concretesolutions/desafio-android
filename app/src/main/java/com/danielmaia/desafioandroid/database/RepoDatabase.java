package com.danielmaia.desafioandroid.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.danielmaia.desafioandroid.dao.PullDao;
import com.danielmaia.desafioandroid.dao.RepoDao;
import com.danielmaia.desafioandroid.model.Pull;
import com.danielmaia.desafioandroid.model.Repo;

@Database(entities = {Repo.class, Pull.class}, version = 1, exportSchema = false)
public abstract class RepoDatabase extends RoomDatabase {

    private static final String DB_NAME = "repo.db";
    private static volatile RepoDatabase instance;

    public static synchronized RepoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static RepoDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                RepoDatabase.class,
                DB_NAME).build();
    }

    public abstract RepoDao getRepoDao();
    public abstract PullDao getPullDao();

}
