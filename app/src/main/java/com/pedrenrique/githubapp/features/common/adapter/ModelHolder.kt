package com.pedrenrique.githubapp.features.common.adapter

import com.pedrenrique.githubapp.features.common.adapter.factory.TypesFactory

abstract class ModelHolder {
    abstract fun type(typesFactory: TypesFactory): Int
}