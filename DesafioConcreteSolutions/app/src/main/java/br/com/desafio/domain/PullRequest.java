package br.com.desafio.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC, suppressConstructorProperties = true)
public class PullRequest implements Serializable{
    private User user;
    private String title;
    @SerializedName("created_at")
    private Date createAt;
    private String body;
    @SerializedName("html_url")
    private String url;
    private Head head;
    private String state;

}
