package br.com.ribamar.concrete.model.data.repository;

import br.com.ribamar.concrete.model.pojos.repositories.Repositories;
import io.reactivex.Observable;

import static br.com.ribamar.concrete.model.data.remote.RetrofitService.getApiService;

public class RepositoryReturn {

    public Observable<Repositories> getRepositories (int page, int perPage){
        return getApiService().getAllRepositories(page, perPage);
    }
}
