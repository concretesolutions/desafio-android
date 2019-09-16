package com.danielmaia.desafioandroid.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.danielmaia.desafioandroid.model.Pull;

import java.util.List;

@Dao
public interface PullDao {

    @Query("SELECT * FROM Pull")
    List<Pull> getPulls();

    @Query("SELECT * FROM Pull WHERE state = 'close' AND repo = :repo")
    List<Pull> getPullClosed(String repo);

    @Query("SELECT * FROM Pull WHERE state = 'open' AND repo = :repo")
    List<Pull> getPullOpened(String repo);

    @Query("SELECT * FROM Pull WHERE id = :id")
    List<Pull> getById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Pull pull);

    @Query("DELETE FROM Pull")
    void deleteAll();

    @Query("DELETE FROM Pull WHERE id = :id")
    void deleteById(long id);

}
