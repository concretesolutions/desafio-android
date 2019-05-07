package com.gdavidpb.github.utils

import com.gdavidpb.github.data.model.api.PullEntry
import com.gdavidpb.github.data.model.api.RepositoryEntry
import com.gdavidpb.github.data.model.api.SearchResultEntry
import com.gdavidpb.github.data.model.api.UserEntry
import com.gdavidpb.github.domain.model.Pull
import com.gdavidpb.github.domain.model.Repository
import com.gdavidpb.github.domain.model.SearchResult
import com.gdavidpb.github.domain.model.User
import java.util.*

/* From api (data layer) to domain layer */

fun UserEntry.toUser() = User(
    id = id,
    login = login,
    url = url,
    avatarUrl = avatar_url ?: ""
)

fun RepositoryEntry.toRepository() = Repository(
    id = id,
    name = name,
    url = url,
    description = description,
    owner = owner.toUser(),
    watchersCount = watchers_count,
    openIssuesCount = open_issues_count,
    forksCount = forks_count,
    createdAt = created_at.parseISO8601(),
    updatedAt = updated_at.parseISO8601()
)

fun PullEntry.toPull() = Pull(
    id = id,
    title = title,
    body = body,
    number = number,
    url = url,
    user = user.toUser(),
    createdAt = created_at.parseISO8601(),
    updatedAt = updated_at.parseISO8601(),
    closedAt = closed_at?.parseISO8601() ?: Date(-1),
    mergedAt = merged_at?.parseISO8601() ?: Date(-1)
)

fun SearchResultEntry<RepositoryEntry>.toRepositorySearchResult() = SearchResult<Repository>(
    totalCount = total_count,
    incompleteResults = incomplete_results,
    items = items.map { it.toRepository() }
)