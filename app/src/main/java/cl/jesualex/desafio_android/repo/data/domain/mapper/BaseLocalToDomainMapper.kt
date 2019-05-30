package cl.jesualex.desafio_android.repo.data.domain.mapper

import cl.jesualex.desafio_android.base.data.Mapper
import cl.jesualex.desafio_android.repo.data.domain.entity.Base
import cl.jesualex.desafio_android.repo.data.local.BaseLocal

/**
 * Created by jesualex on 2019-05-30.
 */
class BaseLocalToDomainMapper: Mapper<BaseLocal, Base>() {
    private val userMapper = UserLocalToDomainMapper()
    private var repoMapper = RepoLocalToDomainMapper()

    override fun map(value: BaseLocal): Base {
        return Base(
            label = value.label,
            ref = value.ref,
            sha = value.sha,
            user = value.user?.let { userMapper.map(it) },
            repo = value.repo?.let { repoMapper.map(it) }
        )
    }

    override fun reverseMap(value: Base): BaseLocal {
        return BaseLocal(
            label = value.label,
            ref = value.ref,
            sha = value.sha,
            user = value.user?.let { userMapper.reverseMap(it) },
            repo = value.repo?.let { repoMapper.reverseMap(it) }
        )
    }
}