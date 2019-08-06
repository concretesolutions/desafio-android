package br.com.githubrepos.data.service;

import br.com.githubrepos.data.entity.RepositoryStatus;

public interface RepositoryServiceApi {

    interface RepositoryServiceCallback<T> {
        void onLoaded(T data);

        void onError(String errorMsg);
    }

    void search(int page, String language, String sort, RepositoryServiceCallback<RepositoryStatus> callback);
}
