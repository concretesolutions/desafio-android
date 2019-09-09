package com.danielmaia.desafio_android.service.list;

import com.danielmaia.desafio_android.service.list.dto.RepoDto;

import java.util.List;

public class ListResponse {

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
