package com.github.api.morepopulargithubapp;

import com.github.api.morepopulargithubapp.model.vo.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RepositoryMock {

    public static List<Repository> getRepositoriesMock(){

        List<Repository> repositories = new ArrayList<>();
        Random gerador = new Random();

        for (int i = 0; i<3; i++){
            int j =  gerador.nextInt();
            Repository repository = new Repository();
            repository.setId(j);
            repository.setName( "Repository Name " + j);
            repository.setDescription("Descripition Name " + j );

            repositories.add(repository);
        }

        return repositories;

    }



}
