package com.danielmaia.desafioandroid.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.danielmaia.desafioandroid.model.Repo;

import java.util.List;

@Dao
public interface RepoDao {

    @Query("SELECT * FROM Repo")
    List<Repo> getRepos();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Repo repo);

    @Query("SELECT * FROM Repo WHERE id = :id")
    List<Repo> getById(int id);

    @Query("DELETE FROM Repo")
    void deleteAll();

    @Query("DELETE FROM Repo WHERE id = :id")
    void deleteById(long id);
}
