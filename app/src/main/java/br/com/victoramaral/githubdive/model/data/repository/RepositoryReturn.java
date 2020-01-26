package br.com.victoramaral.githubdive.model.data.repository;

import br.com.victoramaral.githubdive.model.pojos.repositories.Repositories;
import io.reactivex.Observable;

import static br.com.victoramaral.githubdive.model.data.remote.RetrofitService.getApiService;

public class RepositoryReturn {

    public Observable<Repositories> getRepositories (int page, int perPage){
        return getApiService().getAllRepositories(page, perPage);
    }
}
