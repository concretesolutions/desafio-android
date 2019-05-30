package cl.jesualex.desafio_android.repo.data.domain.mapper

import cl.jesualex.desafio_android.base.data.Mapper
import cl.jesualex.desafio_android.repo.data.domain.entity.Link
import cl.jesualex.desafio_android.repo.data.domain.entity.Links
import cl.jesualex.desafio_android.repo.data.domain.entity.Pull
import cl.jesualex.desafio_android.repo.data.local.PullLocal

/**
 * Created by jesualex on 2019-05-30.
 */
class PullLocalToDomainMapper: Mapper<PullLocal, Pull>() {
    private val baseMapper = BaseLocalToDomainMapper()
    private var userMapper = UserLocalToDomainMapper()

    override fun map(value: PullLocal): Pull {
        return Pull(
            url = value.url,
            id = value.id,
            node_id = value.node_id,
            html_url = value.html_url,
            diff_url = value.diff_url,
            patch_url = value.patch_url,
            issue_url = value.issue_url,
            number = value.number,
            state = value.state,
            locked = value.locked,
            title = value.title,
            user = value.user?.let { userMapper.map(it) },
            body = value.body,
            created_at = value.created_at,
            updated_at = value.updated_at,
            closed_at = value.closed_at,
            merged_at = value.merged_at,
            merge_commit_sha = value.merge_commit_sha,
            assignee = value.assignee,
            assignees = value.assignees,
            requested_reviewers = value.requested_reviewers,
            requested_teams = value.requested_teams,
            labels = value.labels,
            milestone = value.milestone,
            commits_url = value.commits_url,
            review_comments_url = value.review_comments_url,
            review_comment_url = value.review_comment_url,
            comments_url = value.comments_url,
            statuses_url = value.statuses_url,
            head = value.head?.let{ baseMapper.map(it) },
            base = value.base?.let{ baseMapper.map(it) },
            _links = Links(
                self = Link(href = value.self),
                html = Link(href = value.html),
                issue = Link(href = value.issue),
                comments = Link(href = value.comments),
                review_comments = Link(href = value.review_comments),
                review_comment = Link(href = value.review_comment),
                commits = Link(href = value.commits),
                statuses = Link(href = value.statuses)
            ),
            author_association = value.author_association
        )
    }

    override fun reverseMap(value: Pull): PullLocal {
        val resp =  PullLocal(
            url = value.url,
            id = value.id,
            node_id = value.node_id,
            html_url = value.html_url,
            diff_url = value.diff_url,
            patch_url = value.patch_url,
            issue_url = value.issue_url,
            number = value.number,
            state = value.state,
            locked = value.locked,
            title = value.title,
            user = value.user?.let{ userMapper.reverseMap(it)},
            body = value.body,
            created_at = value.created_at,
            updated_at = value.updated_at,
            closed_at = value.closed_at,
            merged_at = value.merged_at,
            merge_commit_sha = value.merge_commit_sha,
            assignee = value.assignee,
            milestone = value.milestone,
            commits_url = value.commits_url,
            review_comments_url = value.review_comments_url,
            review_comment_url = value.review_comment_url,
            comments_url = value.comments_url,
            statuses_url = value.statuses_url,
            head = value.head?.let{ baseMapper.reverseMap(it) },
            base = value.base?.let{ baseMapper.reverseMap(it) },
            self = value._links.self.href,
            html = value._links.html.href,
            issue = value._links.issue.href,
            comments = value._links.comments.href,
            review_comments = value._links.review_comments.href,
            review_comment = value._links.review_comment.href,
            commits = value._links.commits.href,
            statuses = value._links.statuses.href,
            author_association = value.author_association
        )

        resp.assignees.addAll(value.assignees)
        resp.requested_reviewers.addAll(value.requested_reviewers)
        resp.requested_teams.addAll(value.requested_teams)
        resp.labels.addAll(value.labels)

        return resp
    }
}