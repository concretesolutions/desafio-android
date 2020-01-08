package com.example.github_api_concrete.model.repository;

import com.example.github_api_concrete.model.pojo.RepositoriesResult;

import io.reactivex.Observable;

import static com.example.github_api_concrete.model.data.remote.RetrofitService.getApiService;

public class GitHubRepository {

    public Observable<RepositoriesResult> getRepositories(String language, String sort, int page) {
        return getApiService().getAllRepositories(language, sort, page);
    }
}
