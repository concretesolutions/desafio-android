package com.example.vladi.consultagit.io.response;

import com.example.vladi.consultagit.models.PullRequest;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PullsResponse {

    @SerializedName("")
    private ArrayList<PullRequest> pulls;

    public ArrayList<PullRequest> getPulls() {
        return pulls;
    }

    public void setPulls(ArrayList<PullRequest> pulls) {
        this.pulls = pulls;
    }
}
