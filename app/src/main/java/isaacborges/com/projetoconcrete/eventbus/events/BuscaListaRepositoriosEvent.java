package isaacborges.com.projetoconcrete.eventbus.events;

import java.util.List;

import isaacborges.com.projetoconcrete.model.Repositorio;

public class BuscaListaRepositoriosEvent {

    private List<Repositorio> repositorios;

    public BuscaListaRepositoriosEvent(List<Repositorio> repositorios) {
        this.repositorios = repositorios;
    }

    public List<Repositorio> getRepositorios() {
        return repositorios;
    }

}
