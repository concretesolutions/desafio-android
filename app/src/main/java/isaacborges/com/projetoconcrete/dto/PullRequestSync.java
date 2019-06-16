package isaacborges.com.projetoconcrete.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

import isaacborges.com.projetoconcrete.model.PullRequest;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(shape= JsonFormat.Shape.ARRAY)
public class PullRequestSync {

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<PullRequest> listaDePullRequests;

    public List<PullRequest> getListaDePullRequests() {
        return listaDePullRequests;
    }

    public void setListaDePullRequests(List<PullRequest> listaDePullRequests) {
        this.listaDePullRequests = listaDePullRequests;
    }
}
