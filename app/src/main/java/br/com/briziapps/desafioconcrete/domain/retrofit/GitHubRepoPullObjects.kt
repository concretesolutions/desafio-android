package br.com.briziapps.desafioconcrete.domain.retrofit

import com.google.gson.annotations.SerializedName

class GitHubRepoPullObjects {

    @field:SerializedName("title")
    var titleOfPull:String? = null

    @field:SerializedName("body")
    var bodyOfPull:String? = null

    @field:SerializedName("created_at")
    var dateOfPull:String? = null

    @field:SerializedName("html_url")
    var urlRepo:String? = null

    @field:SerializedName("user")
    var user:User? = null

    @field:SerializedName("head")
    var head:Head? = null

    inner class User{

        @field:SerializedName("login")
        var userName:String? = null

        @field:SerializedName("avatar_url")
        var userGravatar:String? = null

    }

    inner class Head{

        @field:SerializedName("label")
        var label:String? = null

    }


}