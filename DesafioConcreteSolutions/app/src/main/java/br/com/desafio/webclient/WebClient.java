package br.com.desafio.webclient;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.util.List;

import br.com.desafio.domain.PullRequest;
import br.com.desafio.domain.Repositories;

@Rest(rootUrl = "https://api.github.com", converters = {GsonHttpMessageConverter.class})
public interface WebClient extends RestClientErrorHandling{

    @Get("/search/repositories?q=language:{language}&sort=stars&page={page}")
    public Repositories findRepositoies(Integer page, String language);

    @Get("/repos/{owner}/{repository}/pulls")
    public List<PullRequest> findPullRequests(String owner, String repository);
}