package br.com.githubrepos.data.service;

import br.com.githubrepos.data.entity.User;

public interface UserServiceApi {

    interface UserServiceCallback<T> {
        void onLoaded(T data);
    }

    void find(String userLogin, UserServiceCallback<User> callback);
}
