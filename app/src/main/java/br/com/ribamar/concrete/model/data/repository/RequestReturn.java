package br.com.ribamar.concrete.model.data.repository;

import java.util.List;

import br.com.ribamar.concrete.model.pojos.requests.Request;
import io.reactivex.Observable;

import static br.com.ribamar.concrete.model.data.remote.RetrofitService.getApiService;


public class RequestReturn {

    public Observable<List<Request>> getRequests(String owner, String repository) {
        return getApiService().getAllPullRequests(owner, repository);
    }

}
