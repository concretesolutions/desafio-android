package br.com.concrete.model;

import java.util.List;

public class RetornoRepositorio {

    private List<Repositorio> items;

    public List<Repositorio> getItems() {
        return items;
    }

    public void setItems(List<Repositorio> items) {
        this.items = items;
    }

    public class Repositorio {

        private String name;
        private String description;
        private int forks_count;
        private int stargazers_count;
        private Owner owner;

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

        public int getForks_count() {
            return forks_count;
        }

        public void setForks_count(int forks_count) {
            this.forks_count = forks_count;
        }

        public int getStargazers_count() {
            return stargazers_count;
        }

        public void setStargazers_count(int stargazers_count) {
            this.stargazers_count = stargazers_count;
        }

        public Owner getOwner() {
            return owner;
        }

        public void setOwner(Owner owner) {
            this.owner = owner;
        }

        public class Owner {

            private String login;
            private String avatar_url;

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
        }
    }
}