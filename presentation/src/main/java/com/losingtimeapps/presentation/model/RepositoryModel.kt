package com.losingtimeapps.presentation.model

data class RepositoryModel(val id:Long,
                           val name:String,
                           val description:String,
                           val startAmount:String,
                           val forksAmount:String,
                           val authorModel: AuthorModel
)
