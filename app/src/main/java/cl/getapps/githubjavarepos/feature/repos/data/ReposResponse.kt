package cl.getapps.githubjavarepos.feature.repos.data

import cl.getapps.githubjavarepos.core.extension.DataRepo
import cl.getapps.githubjavarepos.core.extension.DomainRepo
import com.google.gson.annotations.SerializedName

data class ReposResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    @SerializedName("repos")
    val repos: List<Repo>
) {
    fun toDomainRepos() = repos.mapTo(mutableListOf<DomainRepo>(), fromDataRepo)
}

private val fromDataRepo = {
        dataRepo: DataRepo -> dataRepo.toDomainRepo(dataRepo)
}