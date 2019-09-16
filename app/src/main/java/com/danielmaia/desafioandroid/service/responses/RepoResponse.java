package com.danielmaia.desafioandroid.service.responses;

import com.danielmaia.desafioandroid.service.responses.dto.RepoDto;

import java.util.List;

public class RepoResponse {
    long total_count;
    List<RepoDto> items;

    public long getTotal_count() {
        return total_count;
    }

    public void setTotal_count(long total_count) {
        this.total_count = total_count;
    }

    public List<RepoDto> getItems() {
        return items;
    }

    public void setItems(List<RepoDto> items) {
        this.items = items;
    }

}
