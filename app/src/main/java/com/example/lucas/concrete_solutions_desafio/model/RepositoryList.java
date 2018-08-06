package com.example.lucas.concrete_solutions_desafio.model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RepositoryList {

    @SerializedName("items")
    private ArrayList<Repository> repositories = new ArrayList<>();

    public RepositoryList() {

    }

    public int repositoriesCount (){
        return repositories.size();
    }

    public Repository getRepositoryByPosition(int position){
        return repositories.get(position);
    }

    public List<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(ArrayList<Repository> repositories) {
        this.repositories = repositories;
    }

    public void setRepositories(RepositoryList repositories) {
        int count = repositories.repositoriesCount();


         for (int i=0;i<count; i++){
            this.repositories.add(repositories.getRepositoryByPosition(i));
        }
    }

    public void setRepositories(Repository repository) {
            this.repositories.add(repository);
        }

    public void clear() {
            repositories.clear();
    }
}
