package br.com.githubjavapop.entidade;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListaRepositorio {

    @SerializedName("items")
    @Expose
    private List<Repositorio> listaRepositorio = null;

    public ListaRepositorio() {
    }

    public ListaRepositorio(List<Repositorio> listaRepositorio) {
        this.listaRepositorio = listaRepositorio;
    }

    public List<Repositorio> getListaRepositorio() {
        return listaRepositorio;
    }

    public void setListaRepositorio(List<Repositorio> listaRepositorio) {
        this.listaRepositorio = listaRepositorio;
    }
}
