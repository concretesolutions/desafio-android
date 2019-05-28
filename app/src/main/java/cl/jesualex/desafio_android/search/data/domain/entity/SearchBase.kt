package cl.jesualex.desafio_android.search.data.domain.entity

import cl.jesualex.desafio_android.repo.data.domain.entity.Repo

/**
 * Created by jesualex on 2019-05-28.
 */

data class SearchBase (
	val total_count : Int,
	val incomplete_results : Boolean,
	val items : List<Repo>
)