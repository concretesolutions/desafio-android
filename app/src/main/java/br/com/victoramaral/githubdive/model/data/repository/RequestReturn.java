package br.com.victoramaral.githubdive.model.data.repository;

import java.util.List;

import br.com.victoramaral.githubdive.model.pojos.requests.Request;
import io.reactivex.Observable;

import static br.com.victoramaral.githubdive.model.data.remote.RetrofitService.getApiService;


public class RequestReturn {

    public Observable<List<Request>> getRequests(String owner, String repository) {
        return getApiService().getAllPullRequests(owner, repository);
    }

}
