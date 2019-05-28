package cl.jesualex.desafio_android.repo.data.remote

import cl.jesualex.desafio_android.repo.data.utils.RepoConst
import cl.jesualex.desafio_android.repo.data.domain.entity.Pull
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by jesualex on 2019-05-28.
 */
interface RepoApi {
    @GET("{${RepoConst.USER_KEY}}/{${RepoConst.REPO_KEY}}/pulls") fun getPulls(
        @Path(RepoConst.USER_KEY) user: String,
        @Path(RepoConst.REPO_KEY) repo: String
    ): Observable<List<Pull>>

    @GET("{${RepoConst.REPO_FULL_NAME}}/pulls") fun getPulls(
        @Path(RepoConst.REPO_FULL_NAME) repoFull: String
    ): Observable<List<Pull>>
}