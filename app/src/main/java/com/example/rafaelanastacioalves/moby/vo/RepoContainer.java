package com.example.rafaelanastacioalves.moby.vo;

import java.util.List;

public class RepoContainer  {
    private List<Repo> items;

    public RepoContainer() {
        super();
    }

    public RepoContainer(List<Repo> listRepo){
        this.items = listRepo;
    }

    public List<Repo> getRepoList() {
        return items;
    }
}

