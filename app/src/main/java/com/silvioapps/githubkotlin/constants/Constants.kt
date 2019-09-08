package com.silvioapps.githubkotlin.constants

class Constants {
    companion object {
        const val API_BASE_URL = "https://api.github.com/"
        const val LIST = "search/repositories"
        const val TIMEOUT : Long = 15
        const val QUERY = "language:Java"
        const val SORT = "stars"
        const val DETAILS = "repos/{author}/{repo}/pulls"
        const val AUTHOR = "author"
        const val REPO = "repo"
    }
}
