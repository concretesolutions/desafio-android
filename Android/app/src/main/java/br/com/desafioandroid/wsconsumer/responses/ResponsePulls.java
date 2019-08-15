package br.com.desafioandroid.wsconsumer.responses;

import java.util.List;

import br.com.desafioandroid.model.PullsRequests;
import br.com.desafioandroid.views.PullsActivity;

public class ResponsePulls {
    List<PullsRequests> pullsRequests;

    public List<PullsRequests> getPullsRequests() {
        return pullsRequests;
    }

    public void setPullsRequests(List<PullsRequests> pullsRequests) {
        this.pullsRequests = pullsRequests;
    }
}
