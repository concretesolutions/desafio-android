package br.com.desafio.webclient;

import android.support.test.rule.ActivityTestRule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import br.com.desafio.domain.PullRequest;
import br.com.desafio.domain.Repositories;
import br.com.desafio.ui.RepositoryActivity_;

public class WebClientTest {
    @Rule
    public final ActivityTestRule<RepositoryActivity_> main = new ActivityTestRule<>(RepositoryActivity_.class);

    private final int positionOfTest = 0;
    private final Integer page = 1;
    private final String language = "java";

    WebClient webClient;

    @Test
    public void findRepositoies(){
        WebClient webClient = new WebClient_(main.getActivity());

        Repositories  repositories = webClient.findRepositoies(page, language);

        Assert.assertTrue(repositories != null);
    }

    @Test
    public void countResults(){
        WebClient webClient = new WebClient_(main.getActivity());

        Repositories repositories = webClient.findRepositoies(page, language);
        Assert.assertTrue(repositories.getItems().size() > 0);
    }

    @Test
    public void  findPullRequests(){
        WebClient webClient = new WebClient_(main.getActivity());

        Repositories repositories = webClient.findRepositoies(page, language);

        String owner = repositories.getItems().get(positionOfTest).getOwner().getName();
        String repository = repositories.getItems().get(positionOfTest).getName();

        List<PullRequest> pullRequests = webClient.findPullRequests(owner, repository);


        Assert.assertTrue(pullRequests != null);
    }
}
