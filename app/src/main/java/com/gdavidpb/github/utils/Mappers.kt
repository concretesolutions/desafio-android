package com.gdavidpb.github.utils

import com.gdavidpb.github.data.model.api.PullEntry
import com.gdavidpb.github.data.model.api.RepositoryEntry
import com.gdavidpb.github.data.model.api.SearchResultEntry
import com.gdavidpb.github.data.model.api.UserEntry
import com.gdavidpb.github.data.model.database.EmbeddedUser
import com.gdavidpb.github.data.model.database.PullEntity
import com.gdavidpb.github.data.model.database.RepositoryEntity
import com.gdavidpb.github.domain.model.Pull
import com.gdavidpb.github.domain.model.Repository
import com.gdavidpb.github.domain.model.SearchResult
import com.gdavidpb.github.domain.model.User
import com.gdavidpb.github.presentation.model.PullItem
import com.gdavidpb.github.presentation.model.RepositoryItem
import java.util.*

/* From api to domain model */

fun UserEntry.toUser() = User(
    id = id,
    login = login,
    url = html_url,
    avatarUrl = avatar_url ?: ""
)

fun RepositoryEntry.toRepository(page: Int) = Repository(
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

fun PullEntry.toPull() = Pull(
    id = node_id,
    title = title,
    body = body ?: "",
    number = number,
    url = html_url,
    user = user.toUser(),
    createdAt = created_at.parseISO8601(),
    updatedAt = updated_at.parseISO8601(),
    closedAt = closed_at?.parseISO8601() ?: Date(-1),
    mergedAt = merged_at?.parseISO8601() ?: Date(-1)
)

fun SearchResultEntry<RepositoryEntry>.toRepositorySearchResult(page: Int) = SearchResult(
    totalCount = total_count,
    incompleteResults = incomplete_results,
    items = items.map { it.toRepository(page) }
)

/* From database to domain model */

fun EmbeddedUser.toUser() = User(
    id = id,
    login = login,
    url = url,
    avatarUrl = avatarUrl
)

fun PullEntity.toPull() = Pull(
    id = id,
    title = title,
    body = body,
    number = number,
    url = url,
    user = user.toUser(),
    createdAt = Date(createdAt),
    updatedAt = Date(updatedAt),
    closedAt = Date(closedAt),
    mergedAt = Date(mergedAt)
)

/* From domain to database model */

fun User.toEmbeddedUser() = EmbeddedUser(
    id = id,
    login = login,
    url = url,
    avatarUrl = avatarUrl
)

fun Repository.toRepositoryEntity() = RepositoryEntity(
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

fun Pull.toPullEntity(repository: String) = PullEntity(
    id = id,
    repository = repository,
    title = title,
    body = body,
    number = number,
    url = url,
    user = user.toEmbeddedUser(),
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

fun Pull.toPullItem() = PullItem(
    id = id,
    title = title,
    body = body,
    number = number,
    url = url,
    userLogin = user.login,
    userUrl = user.url,
    userAvatarUrl = user.avatarUrl,
    createdAt = createdAt.format("d MMM yyyy"),
    updatedAt = updatedAt.format("d MMM yyyy"),
    closedAt = closedAt.format("d MMM yyyy"),
    mergedAt = mergedAt.format("d MMM yyyy")
)