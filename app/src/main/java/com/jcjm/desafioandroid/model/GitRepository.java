package com.jcjm.desafioandroid.model;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

public class GitRepository {
    private String id;
    private String name;
    private String description;
    private Owner owner = new Owner();
    private String forks;
    private String stargazers_count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getForks() {
        return forks;
    }

    public void setForks(String forks) {
        this.forks = forks;
    }

    public String getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(String stargazers_count) {
        this.stargazers_count = stargazers_count;
    }


    public static DiffUtil.ItemCallback<GitRepository> DIFF_CALLBACK = new DiffUtil.ItemCallback<GitRepository>() {
        @Override
        public boolean areItemsTheSame(@NonNull GitRepository oldItem, @NonNull GitRepository newItem) {
            return oldItem.id.equals(newItem.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull GitRepository oldItem, @NonNull GitRepository newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GitRepository that = (GitRepository) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (owner != null ? !owner.equals(that.owner) : that.owner != null) return false;
        if (forks != null ? !forks.equals(that.forks) : that.forks != null) return false;
        return stargazers_count != null ? stargazers_count.equals(that.stargazers_count) : that.stargazers_count == null;
    }

}
