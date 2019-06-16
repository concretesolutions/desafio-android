package isaacborges.com.projetoconcrete.dto;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import isaacborges.com.projetoconcrete.model.Repositorio;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RepositoriosSyncTest {

    private RepositoriosSync repositoriosSync = new RepositoriosSync();

    @Test
    public void deve_DevolverListaDeRepositorios_QuandoReceberListaDeRepositorios(){
        List<Repositorio> listaDeRepositorios = new ArrayList<>();
        listaDeRepositorios.add(new Repositorio());
        listaDeRepositorios.add(new Repositorio());
        listaDeRepositorios.add(new Repositorio());
        listaDeRepositorios.add(new Repositorio());
        listaDeRepositorios.add(new Repositorio());

        repositoriosSync.setListaDeRepositorios(listaDeRepositorios);

        List<Repositorio> listaDeRepositoriosDevolvidos = repositoriosSync.getListaDeRepositorios();

        assertThat(listaDeRepositoriosDevolvidos, Matchers.<Repositorio>hasSize(5));
    }

    @Test
    public void deve_DevolverNull_QuandoNaoReceberListaDeRepositorios(){
        List<Repositorio> listaDeRepositoriosDevolvidos = repositoriosSync.getListaDeRepositorios();
        assertThat(listaDeRepositoriosDevolvidos, is(equalTo(null)));
    }

}
