package cl.jesualex.desafio_android.repo.data.domain.entity

/**
 * Created by jesualex on 2019-05-28.
 */

data class Base (
	val label : String,
	val ref : String,
	val sha : String,
	val user : User?,
	val repo : Repo?
)