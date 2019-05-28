package cl.jesualex.desafio_android.repo.data.domain.entity

/**
 * Created by jesualex on 2019-05-28.
 */

data class RepoBase (
	val total_count : Int,
	val incomplete_results : Boolean,
	val items : List<Repo>
)