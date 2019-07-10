package com.example.javapop.Events;

import com.example.javapop.Models.Repository;

import java.util.List;

public class RepositoryListEvent {
    private List<Repository> repository;

    public RepositoryListEvent(List<Repository> repository) {
        this.repository = repository;
    }

    public List<Repository> getRepository() {
        return repository;
    }
}
