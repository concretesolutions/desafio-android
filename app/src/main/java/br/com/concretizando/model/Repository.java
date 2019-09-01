package br.com.concretizando.model;

public class Repository {

    private String name;
    private String description;
    private Long stargazers_count;
    private Long forks;
    private Owner owner;

    public Repository() {

        this.name = "";
        this.description = "";
        this.stargazers_count = 0L;
        this.forks = 0L;
        this.owner = new Owner();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(Long stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public Long getForks() {
        return forks;
    }

    public void setForks(Long forks) {
        this.forks = forks;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
