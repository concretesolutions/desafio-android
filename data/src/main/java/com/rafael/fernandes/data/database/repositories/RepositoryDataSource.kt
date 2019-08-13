package com.rafael.fernandes.data.database.repositories

import com.rafael.fernandes.data.database.dao.RepositoryDao
import com.rafael.fernandes.domain.model.Item
import com.rafael.fernandes.domain.repositories.IOFFLineRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class RepositoryDataSource @Inject constructor(private val repositoryDao: RepositoryDao) :
    IOFFLineRepository {

    override fun save(item: Item): Long {
        return repositoryDao.insert(item)
    }

    override fun retrieveAllRepositoriesSaved(): Observable<MutableList<Item>> {
        return repositoryDao.getAllRespositoriesSaved()
    }

    override fun getRepositoryById(id: Int): Single<Item> {
        return repositoryDao.getRepositoryById(id)
    }
}