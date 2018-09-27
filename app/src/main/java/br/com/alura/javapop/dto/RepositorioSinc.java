package br.com.alura.javapop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import br.com.alura.javapop.model.Repositorio;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositorioSinc {

    @JsonProperty("items")
    private  List<Repositorio> repositorios;

    public List<Repositorio> getRepositorios() {
        return repositorios;
    }
}
