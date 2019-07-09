package app.kelvao.javapop.home

interface HomeContract {
    interface IView {
    fun notifyFetchRepositoriesSuccess()
    fun notifyFetchRepositoriesError()
}
    interface IPresenter {
        fun onViewReady()
        fun fetchRepositories(page: Int)
        fun onDestroy()
    }
}