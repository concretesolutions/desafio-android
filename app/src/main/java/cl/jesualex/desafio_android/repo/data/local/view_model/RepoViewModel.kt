package cl.jesualex.desafio_android.repo.data.local.view_model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import cl.jesualex.desafio_android.repo.data.domain.entity.Repo
import cl.jesualex.desafio_android.repo.data.domain.mapper.RepoLocalToDomainMapper
import cl.jesualex.desafio_android.repo.data.local.repository.RepoLocalRepo

/**
 * Created by jesualex on 2019-05-30.
 */

class RepoViewModel: ViewModel() {
    private val repoLocalRepo = RepoLocalRepo()
    private val repoMapper = RepoLocalToDomainMapper()

    fun getAllLiveData(): LiveData<List<Repo>> = Transformations.map(repoLocalRepo.getAllLiveData()) {
            repoMapper.map(it)
    }
}