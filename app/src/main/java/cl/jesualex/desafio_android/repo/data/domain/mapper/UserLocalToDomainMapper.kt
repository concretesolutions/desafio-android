package cl.jesualex.desafio_android.repo.data.domain.mapper

import cl.jesualex.desafio_android.base.data.Mapper
import cl.jesualex.desafio_android.repo.data.domain.entity.User
import cl.jesualex.desafio_android.repo.data.local.UserLocal

/**
 * Created by jesualex on 2019-05-30.
 */
class UserLocalToDomainMapper: Mapper<UserLocal, User>() {
    override fun map(value: UserLocal): User {
        return User(
            login = value.login,
            id = value.id,
            node_id = value.node_id,
            avatar_url = value.avatar_url,
            gravatar_id = value.gravatar_id,
            url = value.url,
            html_url = value.html_url,
            followers_url = value.followers_url,
            following_url = value.following_url,
            gists_url = value.gists_url,
            starred_url = value.starred_url,
            subscriptions_url = value.subscriptions_url,
            organizations_url = value.organizations_url,
            repos_url = value.repos_url,
            events_url = value.events_url,
            received_events_url = value.received_events_url,
            type = value.type,
            site_admin = value.site_admin
        )
    }

    override fun reverseMap(value: User): UserLocal {
        return UserLocal(
            login = value.login,
            id = value.id,
            node_id = value.node_id,
            avatar_url = value.avatar_url,
            gravatar_id = value.gravatar_id,
            url = value.url,
            html_url = value.html_url,
            followers_url = value.followers_url,
            following_url = value.following_url,
            gists_url = value.gists_url,
            starred_url = value.starred_url,
            subscriptions_url = value.subscriptions_url,
            organizations_url = value.organizations_url,
            repos_url = value.repos_url,
            events_url = value.events_url,
            received_events_url = value.received_events_url,
            type = value.type,
            site_admin = value.site_admin
        )
    }
}