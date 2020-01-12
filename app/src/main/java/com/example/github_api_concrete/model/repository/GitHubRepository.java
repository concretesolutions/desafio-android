package com.example.github_api_concrete.model.repository;

import com.example.github_api_concrete.model.pojo.pullrequests.Response;
import com.example.github_api_concrete.model.pojo.repos.RepositoriesResult;

import java.util.List;

import io.reactivex.Observable;

import static com.example.github_api_concrete.model.data.remote.RetrofitService.getApiService;

public class GitHubRepository {

    public Observable<RepositoriesResult> getRepositories(String language, String sort, int page) {
        return getApiService().getAllRepositories(language, sort, page);
    }

    public Observable<List<Response>> getPRs(String login, String name) {
        return getApiService().getAllPRs(login, name);
    }
}
