package br.com.alura.javapop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Calendar;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PullRequest {

    private String url;

    @JsonProperty("title")
    private String titulo;

    @JsonProperty("body")
    private String corpo;

    @JsonProperty("user")
    private Usuario usuario;

    @JsonProperty("created_at")
    private Calendar data;

}
