package br.com.josef.desafioconcretegit.model.data.repository;

import java.util.List;

import br.com.josef.desafioconcretegit.model.pojo.pull.PullRequest;
import br.com.josef.desafioconcretegit.model.pojo.repositories.GitResult;
import io.reactivex.Observable;

import static br.com.josef.desafioconcretegit.model.data.remote.GitRetrofit.getApiService;

public class GitRepository {

    public Observable<GitResult> getRepositorios(int page) {
        return getApiService().getAllRepositorios(page);
    }

    public Observable<List<PullRequest>> getPullRequest(String creatorString, String repoString){
        return getApiService().getPullRequests(creatorString, repoString);
    }


}
