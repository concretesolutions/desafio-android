package br.com.briziapps.desafioconcrete.domain.retrofit

import com.google.gson.annotations.SerializedName

 class GitHubRepoObjects {

     @field:SerializedName("id")
     var id:Int? = null

     @field:SerializedName("name")
     var repositorieName:String? = null

     @field:SerializedName("full_name")
     var repositorieFullName:String? = null

     @field:SerializedName("description")
     var repositorieDescription:String? = null

     @field:SerializedName("forks_count")
     var forksCount:Int? = null

     @field:SerializedName("stargazers_count")
     var  starsCount:Int? = null

     @field:SerializedName("owner")
     var owner:Owner? = null


     inner class Owner{

         @field:SerializedName("login")
         var ownerName:String? = null

         @field:SerializedName("avatar_url")
         var ownerAvatar:String? = null
     }
}