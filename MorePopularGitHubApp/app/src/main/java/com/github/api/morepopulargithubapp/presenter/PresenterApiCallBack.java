package com.github.api.morepopulargithubapp.presenter;

import java.util.Map;

 public interface PresenterApiCallBack <T>{
    void notifySuccess(T object);
    void notifyError(Map error );
}
