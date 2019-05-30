package cl.jesualex.desafio_android.repo.data.domain.use_case

import cl.jesualex.desafio_android.base.remote.UseCase
import cl.jesualex.desafio_android.repo.data.domain.entity.Pull
import cl.jesualex.desafio_android.repo.data.remote.RepoApiImpl
import cl.jesualex.desafio_android.search.data.domain.entity.SearchBase
import cl.jesualex.desafio_android.search.data.remote.SearchApiImpl
import io.reactivex.Observable

/**
 * Created by jesualex on 2019-05-28.
 */
class SearchPullsUseCase: UseCase<List<Pull>, String>() {
    override fun createObservableUseCase(param: String?): Observable<List<Pull>>? {
        return param?.let { RepoApiImpl.getInstance().getPulls(it) }
    }
}