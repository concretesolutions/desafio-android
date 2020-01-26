
package br.com.victoramaral.githubdive.model.pojos.requests;

import com.google.gson.annotations.Expose;

public class Head {

    @Expose
    private String label;
    @Expose
    private String ref;
    @Expose
    private Repo repo;
    @Expose
    private String sha;
    @Expose
    private User user;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
