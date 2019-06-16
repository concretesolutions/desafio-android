package isaacborges.com.projetoconcrete.dto;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import isaacborges.com.projetoconcrete.model.PullRequest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PullRequestSyncTest {

    private PullRequestSync pullRequestSync = new PullRequestSync();

    @Test
    public void deve_DevolverListaDePullRequests_QuandoReceberListaDePullRequests(){
        List<PullRequest> listaDePullRequests = new ArrayList<>();
        listaDePullRequests.add(new PullRequest());
        listaDePullRequests.add(new PullRequest());
        listaDePullRequests.add(new PullRequest());
        listaDePullRequests.add(new PullRequest());
        listaDePullRequests.add(new PullRequest());

        pullRequestSync.setListaDePullRequests(listaDePullRequests);
        List<PullRequest> listaDePullRequestsDevolvida = pullRequestSync.getListaDePullRequests();

        assertThat(listaDePullRequestsDevolvida, Matchers.<PullRequest>hasSize(5));
    }

    @Test
    public void deve_DevolverNull_QuandoNaoReceberListaDePullRequests(){
        List<PullRequest> listaDePullRequestsDevolvida = pullRequestSync.getListaDePullRequests();
        Assert.assertThat(listaDePullRequestsDevolvida, is(equalTo(null)));
    }


}
