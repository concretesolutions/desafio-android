package com.rafael.fernandes.desafioconcrete.presentation.resources


sealed class ResourceState {
    object INITIAL : ResourceState()
    object LOADING : ResourceState()
    object SUCCESS : ResourceState()
    object ERROR : ResourceState()
}