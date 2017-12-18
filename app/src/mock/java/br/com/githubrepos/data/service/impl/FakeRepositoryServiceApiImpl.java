package br.com.githubrepos.data.service.impl;

import br.com.githubrepos.data.entity.RepositoryStatus;
import br.com.githubrepos.data.service.RepositoryServiceApi;

public class FakeRepositoryServiceApiImpl implements RepositoryServiceApi {


    @Override
    public void search(int page, String language, String sort, RepositoryServiceCallback<RepositoryStatus> callback) {

    }
}
