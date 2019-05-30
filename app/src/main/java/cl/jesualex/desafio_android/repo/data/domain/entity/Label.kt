package cl.jesualex.desafio_android.repo.data.domain.entity

/**
 * Created by jesualex on 2019-05-30.
 */

data class Label (
    val id : Int,
    val node_id : String,
    val url : String,
    val name : String,
    val color : String,
    val default : Boolean
)