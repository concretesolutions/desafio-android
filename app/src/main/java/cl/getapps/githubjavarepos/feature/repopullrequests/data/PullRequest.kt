import cl.getapps.githubjavarepos.feature.repopullrequests.domain.PullRequest

/*
Copyright (c) 2019 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class PullRequest(

    val url: String,
    val id: Int,
    val node_id: String,
    val html_url: String,
    val diff_url: String,
    val patch_url: String,
    val issue_url: String,
    val number: Int,
    val state: String,
    val locked: Boolean,
    val title: String,
    val user: User,
    val body: String,
    val created_at: String,
    val updated_at: String,
    val closed_at: String,
    val merged_at: String,
    val merge_commit_sha: String,
    val assignee: String,
    val assignees: List<String>,
    val requested_reviewers: List<String>,
    val requested_teams: List<String>,
    val labels: List<String>,
    val milestone: String,
    val commits_url: String,
    val review_comments_url: String,
    val review_comment_url: String,
    val comments_url: String,
    val statuses_url: String,
    val head: Head,
    val base: Base,
    val _links: _links,
    val author_association: String
) {
    fun toPullRequest() = PullRequest(user.toUSer(), title, body, created_at)
}