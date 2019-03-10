package com.hako.githubapi.util

sealed class NetworkStatus {
    object Ready : NetworkStatus()
    object Loading : NetworkStatus()
}