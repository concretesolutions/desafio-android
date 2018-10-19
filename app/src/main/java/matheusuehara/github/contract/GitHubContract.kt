package matheusuehara.github.contract

import matheusuehara.github.api.GitHubApi

interface GitHubContract {

    interface Service{
        fun getAPI(): GitHubApi
    }
}
