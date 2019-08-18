package br.com.desafioandroid.model;

import java.util.List;

public class ListRepositories {
    List<Repository> repositoryList;

    public ListRepositories(List<Repository> repositories) {
        this.repositoryList = repositories;
    }

    public List<Repository> getRepositoryList() {
        return repositoryList;
    }

    public void setRepositoryList(List<Repository> repositoryList) {
        this.repositoryList = repositoryList;
    }
}
