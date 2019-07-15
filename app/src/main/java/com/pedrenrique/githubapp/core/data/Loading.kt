package com.pedrenrique.githubapp.core.data

sealed class Loading {
    object Full : Loading()
    object Item : Loading()
}