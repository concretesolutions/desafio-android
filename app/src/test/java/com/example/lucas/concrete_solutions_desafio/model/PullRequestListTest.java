package com.example.lucas.concrete_solutions_desafio.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PullRequestListTest {

    Date date = new Date(1990,01,22,9,32,56);
    PullRequestList pullRequests = new PullRequestList();
    int pullRequestsSize = 10;
    @Test
    public void testGetPullRequestByPosition() {
        /*execução - início*/
        for(int count=0; count<10; count++){
            PullRequest pullRequest = pullRequests.getPullRequestByPosition(count);

            /*test*/
            assertThat(pullRequest.getTitle(), is(pullRequests.getPullRequestByPosition(count).getTitle()));
            assertThat(pullRequest.getBody(), is(pullRequests.getPullRequestByPosition(count).getBody()));
            assertThat(pullRequest.getCreated_at(), is(pullRequests.getPullRequestByPosition(count).getCreated_at()));
            assertThat(pullRequest.getUser(),is(pullRequests.getPullRequestByPosition(count).getUser()));
        }
        /*execução - fim*/
    }

    @Test
    public void testSetPullRequests() {

        /*test*/
        assertThat(pullRequests.pullRequestsCount(), is(pullRequestsSize));

        /*montagem do cenário - início*/
        for(int count=pullRequestsSize; count<pullRequestsSize*2; count++){
            User user = new User("login"+count, "avatar_url"+count);
            PullRequest pullRequest = new PullRequest("title"+count, "body"+count,  date, user);
            pullRequests.setPullRequests(pullRequest);
        }
        /*montagem do cenário - fim*/


        /*test*/
        assertThat(pullRequests.pullRequestsCount(), is(pullRequestsSize*2));


    }

    @Before
    public void setUp(){
        /*montagem do cenário - início*/

        for(int count=0; count<10; count++){
            User user = new User("login"+count, "avatar_url"+count);
            PullRequest pullRequest = new PullRequest("title"+count, "body"+count,  date, user);
            pullRequests.setPullRequests(pullRequest);
        }
        /*montagem do cenário - fim*/
    }

    @After
    public void tearDown(){
        pullRequests.clear();
    }

}