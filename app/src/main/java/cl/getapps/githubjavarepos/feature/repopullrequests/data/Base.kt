package cl.getapps.githubjavarepos.feature.repopullrequests.data


data class Base (

	val label : String,
	val ref : String,
	val sha : String,
	val user : User,
	val repo : Repo
)