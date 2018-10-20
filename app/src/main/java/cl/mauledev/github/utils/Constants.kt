package cl.mauledev.github.utils

class Constants {

    companion object {

        const val BASE_URL = "https://api.github.com"

        const val REMOTE_CONNECT_TIMEOUT = 1L
        const val REMOTE_READ_TIMEOUT = 1L
        const val REMOTE_WRITE_TIMEOUT = 2L

        const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"

        const val DEFAULT_QUERY = "language:Java"
        const val DEFAULT_SORT = "stars"

        const val PAGE = "page"
        const val REPO = "repo"
        const val PR_TITLE = "title"
        const val PR_URL = "url"
        const val RECYCLER_STATE = "recycler_state"

        const val CONNECTIVITY_ACTION = "cl.mauledev.github.CONNECTIVITY_CHANGE"
    }
}