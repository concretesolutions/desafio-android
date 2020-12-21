package com.ccortez.desafioconcrete.model

import com.ccortez.desafioconcreteapplication.service.model.Head
import com.ccortez.desafioconcreteapplication.service.model.Milestone
import com.ccortez.desafioconcreteapplication.service.model.User
import java.util.*

data class PullRequest (
    var id: Long = 0,
    var title: String? = null,
    var body: String? = null,
    var state: String? = null,
    var html_url: String? = null,
    var updatedAt: Date? = null,
    var createdAt: Date? = null,
    var head: Head? = null,
    var user: User? = null,
    var milestone: Milestone?

)

//fun getCreatedAtString(): String {
//    if (updatedAt == null) {
//        if (createdAt == null)
//            return "--/--/--";
//        else
//            return createdAt.toString();
//    } else
//        return updatedAt.toString();
//}