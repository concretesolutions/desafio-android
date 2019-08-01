package matheusuehara.github.features.repository

import matheusuehara.github.data.model.Repository

interface RepositoryClickListener {

    fun onClick(repository: Repository)
}