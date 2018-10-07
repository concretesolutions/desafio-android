package com.br.apigithub.interfaces;

/**
 * Created by rlima on 04/10/18.
 */

public interface IGithubServiceProvider {
    void listReposJava(String sort, Integer page, INotifyViewModelAboutService listener);

    void getPulls(String ownerRepo, String repoName, Integer page, INotifyViewModelAboutService listener);

}
