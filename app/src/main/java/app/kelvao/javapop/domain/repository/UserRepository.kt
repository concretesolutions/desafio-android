package app.kelvao.javapop.domain.repository

import app.kelvao.javapop.domain.network.RestClient

object UserRepository {
    private val userService = RestClient.userService

    fun fetchUser(login: String) = userService.getUser(login)
}