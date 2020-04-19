package com.example.githubtest.features.repository

import com.example.githubtest.data.model.Repository

interface RepositoryClickListener {

    fun onClick(repository: Repository)
}