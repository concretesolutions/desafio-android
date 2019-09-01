package br.com.concretizando.model;

public class PullRequest {

    private String html_url;
    private String title;
    private String created_at;
    private String body;
    private Owner user;

    public PullRequest() {

        this.html_url = "";
        this.title = "";
        this.created_at = "";
        this.body = "";
        this.user = new Owner();
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Owner getUser() {
        return user;
    }

    public void setUser(Owner user) {
        this.user = user;
    }
}
