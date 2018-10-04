package com.br.apigithub.interfaces;

import com.br.apigithub.beans.GithubRepository;
import com.br.apigithub.beans.Pull;

import java.util.List;

/**
 * Created by rlima on 04/10/18.
 */

public interface INotifyViewModelAboutService {
    void returnListRepos(List<GithubRepository> list);

    void updateListRepos(List<GithubRepository> list);

    void returnPullRequests(List<Pull> pulls);

    void updatePullRequests(List<Pull> prs);

    void returnInfoAboutPullRequest(List<Pull> pulls);

    void notifyOnError(Throwable throwable);
}
