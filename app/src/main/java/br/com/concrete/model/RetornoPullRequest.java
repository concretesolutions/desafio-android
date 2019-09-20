package br.com.concrete.model;

public class RetornoPullRequest {

    private Usuario user;
    private String title;
    private String created_at;
    private String body;

    public RetornoPullRequest(){}

    public RetornoPullRequest(Usuario user, String title, String created_at, String body) {
        this.user = user;
        this.title = title;
        this.created_at = created_at;
        this.body = body;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
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

    public class Usuario {

        private String login;
        private String avatar_url;
        private String html_url;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getHtml_url() {
            return html_url;
        }

        public void setHtml_url(String html_url) {
            this.html_url = html_url;
        }
    }
}