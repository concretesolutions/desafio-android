package cl.jesualex.desafio_android.repo.data.local.repository

import cl.jesualex.desafio_android.base.presentation.LiveDataRepository
import cl.jesualex.desafio_android.repo.data.domain.entity.Repo
import cl.jesualex.desafio_android.repo.data.domain.mapper.RepoLocalToDomainMapper
import cl.jesualex.desafio_android.repo.data.local.entity.RepoLocal
import io.realm.Realm
import io.realm.Sort

/**
 * Created by jesualex on 2019-05-30.
 */
class RepoLocalRepo {
    private val entity = RepoLocal::class.java
    private val repoMapper = RepoLocalToDomainMapper()

    fun save(repos: List<Repo>){
        Realm.getDefaultInstance().executeTransactionAsync {
            it.copyToRealmOrUpdate(repoMapper.reverseMap(repos))
        }
    }

    fun removeAllCascade(){
        Realm.getDefaultInstance().executeTransactionAsync {
            for (repo in it.where(entity).findAll()){
                repo.cascadeDelete()
            }
        }
    }

    fun getAllLiveData() = LiveDataRepository(Realm.getDefaultInstance().where(entity)
        .sort("stargazers_count", Sort.DESCENDING)
        .findAllAsync())
}