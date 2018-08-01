package com.example.lucas.concrete_solutions_desafio.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RepositoryListTest {

    RepositoryList repositories = new RepositoryList();
    int repositoriesSize = 10;

    @Test
    public void testGetRepositoryByPosition() {

        /*execução - início*/
        for(int count=0; count<10; count++){
            Repository repository = repositories.getRepositoryByPosition(count);

            /*test*/
            assertThat(repository.getName(), is(repositories.getRepositoryByPosition(count).getName()));
            assertThat(repository.getDescription(), is(repositories.getRepositoryByPosition(count).getDescription()));
            assertThat(repository.getForks_count(), is(repositories.getRepositoryByPosition(count).getForks_count()));
            assertThat(repository.getStargazers_count(),is(repositories.getRepositoryByPosition(count).getStargazers_count()));
            assertThat(repository.getUser(), is(repositories.getRepositoryByPosition(count).getUser()));
        }
        /*execução - fim*/
    }

    @Test
    public void testSetRepositoryList() {


        /*test*/
        assertThat(repositories.repositoriesCount(), is(repositoriesSize));

        /*montagem do cenário - início*/
        for(int count=repositoriesSize; count<repositoriesSize*2; count++){
            User user = new User("login"+count, "avatar_url"+count);
            Repository repository = new Repository("name"+count,"description"+count,
                    "stargazers_count"+count,"forks_count"+count,"full_name"+count, user);
            repositories.setRepositories(repository);
        }
        /*montagem do cenário - fim*/

        /*test*/
        assertThat(repositories.repositoriesCount(), is(repositoriesSize*2));

    }

    @Before
    public void setUp(){
        /*montagem do cenário - início*/

        for(int count=0; count<repositoriesSize; count++){
            User user = new User("login"+count, "avatar_url"+count);
            Repository repository = new Repository("name"+count,"description"+count,
                    "stargazers_count"+count,"forks_count"+count,"full_name"+count, user);
            repositories.setRepositories(repository);
        }
        /*montagem do cenário - fim*/
    }

    @After
    public void tearDown(){
        repositories.clear();
    }

}
