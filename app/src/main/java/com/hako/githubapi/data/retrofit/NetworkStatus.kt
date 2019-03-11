package com.hako.githubapi.data.retrofit

sealed class NetworkStatus {
    object Ready : NetworkStatus()
    object Loading : NetworkStatus()
    object Errored : NetworkStatus()
}