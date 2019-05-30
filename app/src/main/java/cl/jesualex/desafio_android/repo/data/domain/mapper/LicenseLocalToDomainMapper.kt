package cl.jesualex.desafio_android.repo.data.domain.mapper

import cl.jesualex.desafio_android.base.data.Mapper
import cl.jesualex.desafio_android.repo.data.domain.entity.License
import cl.jesualex.desafio_android.repo.data.local.entity.LicenseLocal

/**
 * Created by jesualex on 2019-05-30.
 */
class LicenseLocalToDomainMapper: Mapper<LicenseLocal, License>() {
    override fun map(value: LicenseLocal): License {
        return License(
            key = value.key,
            name = value.name,
            spdx_id = value.spdx_id,
            url = value.url,
            node_id = value.node_id
        )
    }

    override fun reverseMap(value: License): LicenseLocal {
        return LicenseLocal(
            key = value.key,
            name = value.name,
            spdx_id = value.spdx_id,
            url = value.url,
            node_id = value.node_id
        )
    }
}