package com.example.desafioconcrete.Objects;


import java.util.List;

public class GithubApiResponse {

    private List<DetailsRepository> items;

    public GithubApiResponse(List<DetailsRepository> items) {
        this.items = items;
    }

    public List<DetailsRepository> getDetailsRepositoryList() {
        return items;
    }

    public void setDetailsRepositoryList(List<DetailsRepository> items) {
        this.items = items;
    }

    public void addDetailsRepositoryList(List<DetailsRepository> detailsRepository){
        this.items.addAll(detailsRepository);
    }
}
