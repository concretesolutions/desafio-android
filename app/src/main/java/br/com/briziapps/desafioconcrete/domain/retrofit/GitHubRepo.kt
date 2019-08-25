package br.com.briziapps.desafioconcrete.domain.retrofit

import com.google.gson.annotations.SerializedName


data class GitHubRepo(

    @field:SerializedName("items")
    var items:List<GitHubRepoObjects>

)