package isaacborges.com.projetoconcrete.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import isaacborges.com.projetoconcrete.model.Repositorio;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoriosSync {

    @JsonProperty("items")
    private List<Repositorio> listaDeRepositorios;

    public List<Repositorio> getListaDeRepositorios() {
        return listaDeRepositorios;
    }

    public void setListaDeRepositorios(List<Repositorio> listaDeRepositorios) {
        this.listaDeRepositorios = listaDeRepositorios;
    }

}
