package br.com.appdesafio.model.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import br.com.appdesafio.model.entity.UrlEntity;


/**
 * class DAO responsible for the crud of the list of urls for the operation of the cache.
 */
@Dao
public interface UrlDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(UrlEntity url);

    @Query("SELECT * FROM UrlEntity WHERE user_name IN (:userName)")
    List<UrlEntity> getUrlPhoto(final String userName);

 /*   @Query("SELECT * FROM UrlEntity")
    List<UrlEntity> getUrlByCategory(final String category);*/





}
