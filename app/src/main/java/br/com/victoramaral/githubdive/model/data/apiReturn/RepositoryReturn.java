package br.com.victoramaral.githubdive.model.data.apiReturn;

import br.com.victoramaral.githubdive.model.pojos.repositories.Item;
import br.com.victoramaral.githubdive.model.pojos.repositories.Repositories;
import io.reactivex.Observable;

import static br.com.victoramaral.githubdive.model.data.remote.RetrofitService.getApiService;

public class RepositoryReturn {

    public Observable<Repositories> getRepositories (int page){
        return getApiService().getAllRepositories(page);
    }
}
