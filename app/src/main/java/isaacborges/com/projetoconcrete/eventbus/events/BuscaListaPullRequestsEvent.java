package isaacborges.com.projetoconcrete.eventbus.events;

import java.util.List;

import isaacborges.com.projetoconcrete.model.PullRequest;

public class BuscaListaPullRequestsEvent {

    private List<PullRequest> pullRequests;

    public BuscaListaPullRequestsEvent(List<PullRequest> pullRequests) {
        this.pullRequests = pullRequests;
    }

    public List<PullRequest> getPullRequests() {
        return pullRequests;
    }
}
