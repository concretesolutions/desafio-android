package com.jmc.desafioandroidkotlin.utils


import com.jmc.desafioandroidkotlin.data.model.api.PullEntry
import com.jmc.desafioandroidkotlin.data.model.api.RepositoryEntry
import com.jmc.desafioandroidkotlin.data.model.api.SearchResultEntry
import com.jmc.desafioandroidkotlin.data.model.api.UserEntry
import com.jmc.desafioandroidkotlin.data.model.database.EmbeddedUser
import com.jmc.desafioandroidkotlin.data.model.database.PullEntity
import com.jmc.desafioandroidkotlin.data.model.database.RepositoryEntity
import com.jmc.desafioandroidkotlin.domain.model.PullModel
import com.jmc.desafioandroidkotlin.domain.model.RepositoryModel
import com.jmc.desafioandroidkotlin.domain.model.SearchResultModel
import com.jmc.desafioandroidkotlin.domain.model.UserModel
import com.jmc.desafioandroidkotlin.presentation.model.PullItem
import com.jmc.desafioandroidkotlin.presentation.model.RepositoryItem
import java.util.*

/* From api to domain model */

fun UserEntry.toUser() = UserModel(
    id = id,
    login = login,
    url = html_url,
    avatarUrl = avatar_url ?: ""
)

fun RepositoryEntry.toRepository(page: Int) = RepositoryModel(
    id = id,
    name = name,
    fullName = full_name,
    url = url,
    description = description ?: "",
    owner = owner.toUser(),
    stargazersCount = stargazers_count,
    watchersCount = watchers_count,
    openIssuesCount = open_issues_count,
    forksCount = forks_count,
    createdAt = created_at.parseISO8601(),
    updatedAt = updated_at.parseISO8601(),
    page = page
)

fun PullEntry.toPull() = PullModel(
    id = node_id,
    title = title,
    body = body ?: "",
    number = number,
    url = html_url,
    userModel = user.toUser(),
    createdAt = created_at.parseISO8601(),
    updatedAt = updated_at.parseISO8601(),
    closedAt = closed_at?.parseISO8601() ?: Date(-1),
    mergedAt = merged_at?.parseISO8601() ?: Date(-1)
)

fun SearchResultEntry<RepositoryEntry>.toRepositorySearchResult(page: Int) = SearchResultModel(
    totalCount = total_count,
    incompleteResults = incomplete_results,
    items = items.map { it.toRepository(page) }
)

/* From database to domain model */

fun EmbeddedUser.toUser() = UserModel(
    id = id,
    login = login,
    url = url,
    avatarUrl = avatarUrl
)

fun PullEntity.toPull() = PullModel(
    id = id,
    title = title,
    body = body,
    number = number,
    url = url,
    userModel = user.toUser(),
    createdAt = Date(createdAt),
    updatedAt = Date(updatedAt),
    closedAt = Date(closedAt),
    mergedAt = Date(mergedAt)
)

/* From domain to database model */

fun UserModel.toEmbeddedUser() = EmbeddedUser(
    id = id,
    login = login,
    url = url,
    avatarUrl = avatarUrl
)

fun RepositoryModel.toRepositoryEntity() = RepositoryEntity(
    id = id,
    name = name,
    fullName = fullName,
    url = url,
    description = description,
    owner = owner.toEmbeddedUser(),
    stargazersCount = stargazersCount,
    watchersCount = watchersCount,
    openIssuesCount = openIssuesCount,
    forksCount = forksCount,
    createdAt = createdAt.time,
    updatedAt = updatedAt.time,
    page = page
)

fun PullModel.toPullEntity(repository: String) = PullEntity(
    id = id,
    repository = repository,
    title = title,
    body = body,
    number = number,
    url = url,
    user = userModel.toEmbeddedUser(),
    createdAt = createdAt.time,
    updatedAt = updatedAt.time,
    closedAt = closedAt.time,
    mergedAt = mergedAt.time
)

/* From database to presentation model */

fun RepositoryEntity.toRepositoryItem() = RepositoryItem(
    id = id,
    name = name,
    fullName = fullName,
    url = url,
    description = description,
    userLogin = owner.login,
    userUrl = owner.url,
    userAvatarUrl = owner.avatarUrl,
    stargazersCount = stargazersCount.readableFormat(),
    watchersCount = watchersCount.readableFormat(),
    openIssuesCount = openIssuesCount.readableFormat(),
    forksCount = forksCount.readableFormat(),
    createdAt = Date(createdAt).format("d MMM yyyy"),
    updatedAt = Date(updatedAt).format("d MMM yyyy"),
    page = page
)

/* From domain to presentation model */

fun PullModel.toPullItem() = PullItem(
    id = id,
    title = title,
    body = body,
    number = number,
    url = url,
    userLogin = userModel.login,
    userUrl = userModel.url,
    userAvatarUrl = userModel.avatarUrl,
    createdAt = createdAt.format("d MMM yyyy"),
    updatedAt = updatedAt.format("d MMM yyyy"),
    closedAt = closedAt.format("d MMM yyyy"),
    mergedAt = mergedAt.format("d MMM yyyy")
)