package cl.jesualex.desafio_android.search.domain.use_case

import cl.jesualex.desafio_android.base.remote.UseCase
import cl.jesualex.desafio_android.search.data.domain.entity.SearchBase
import cl.jesualex.desafio_android.search.data.remote.SearchApiImpl
import io.reactivex.Observable

/**
 * Created by jesualex on 2019-05-28.
 */
class SearchJavaRepoByStartUseCase: UseCase<SearchBase, Int>() {
    private val lang = "Java"
    private val sortBy = "stars"

    override fun createObservableUseCase(param: Int?): Observable<SearchBase>? {
        return param?.let { SearchApiImpl.getInstance().search(lang, sortBy, it) }
    }
}