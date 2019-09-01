package br.com.concretizando.model;

import java.util.ArrayList;
import java.util.List;

public class ReposForStar {

    List<Repository> items;

    public ReposForStar() {

        this.items = new ArrayList<>();
    }

    public List<Repository> getItems() {
        return items;
    }

    public void setItems(List<Repository> items) {
        this.items = items;
    }
}
