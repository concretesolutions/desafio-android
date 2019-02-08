package com.accenture.githubrepositories.pojo

 data class PullRequest (

     var id : Int = 0,
     var user : PullRequestUser,
     var title : String = "",
     var html_url : String = "",
     var body : String = "",
     var created_at : String = ""

)