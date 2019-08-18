package com.desafio.javapop.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResultadoRepo {

    @SerializedName("items")
    private List<Repositorio> repositorios = new ArrayList<Repositorio>();

    public List<Repositorio> getRepositorios() {
        return repositorios;
    }

    public void setRepositorios(List<Repositorio> repositorios) { this.repositorios = repositorios; }

}
