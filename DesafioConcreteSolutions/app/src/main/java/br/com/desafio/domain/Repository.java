package br.com.desafio.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC, suppressConstructorProperties = true)
public class Repository implements Serializable{
    private String name;
    private String description;
    @SerializedName("full_name")
    private String fullName;
    private User owner;
    @SerializedName("stargazers_count")
    private Integer stars;
    @SerializedName("forks_count")
    private Integer forks;
    @Expose
    private List<PullRequest> pullRequests;
}
