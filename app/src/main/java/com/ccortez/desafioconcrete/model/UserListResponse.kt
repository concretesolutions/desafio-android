package com.ccortez.desafioconcrete.model


class UserListResponse(
    var page: Int,
    var per_page: Int,
    var total: Int,
    var total_pages: Int,
    var data: List<UserEntity>
) {
}