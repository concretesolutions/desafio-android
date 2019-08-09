package sergio.com.br.desafioandroid.app

object Constants {

    object END_POINTS {
        const val SEARCH_URL = "search/repositories?q=language:Java&sort=stars"
        const val PULL_REQUEST_URL = "https://api.github.com/repos/{creator}/{repository}/pulls"
        const val PULL_REQUEST_HTML_URL = "https://github.com/{creator}/{repository}/pull/{number}"
    }
}