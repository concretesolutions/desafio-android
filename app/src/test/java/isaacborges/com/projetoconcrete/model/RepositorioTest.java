package isaacborges.com.projetoconcrete.model;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class RepositorioTest {

    //[deve][resultado esperado][estado de teste]

    private Repositorio repositorio = new Repositorio();

    @Test
    public void deve_DevolverName_QuandoReceberName(){
        repositorio.setName("Nome do repositório");
        String nameDevolvido = repositorio.getName();

        assertThat(nameDevolvido, is(equalTo("Nome do repositório")));
    }

    @Test
    public void deve_DevolverDescription_QuandoReceberDescription(){
        repositorio.setDescription("Descrição do repositório");
        String descricaoDevolvida = repositorio.getDescription();

        assertThat(descricaoDevolvida, is(equalTo("Descrição do repositório")));
    }

    @Test
    public void deve_DevolverAutor_QuandoReceberAutor(){

        Autor autor = new Autor();
        autor.setId(1);
        autor.setLogin("isaacborges92");

        repositorio.setAutor(autor);
        Autor autorDevolvido = repositorio.getAutor();

        assertThat(autorDevolvido, is(equalTo(autor)));
    }

    @Test
    public void deve_DevolverForksCount_QuandoReceberForksCount(){
        repositorio.setForks_count(50);
        int forksCountDevolvido = repositorio.getForks_count();

        assertThat(forksCountDevolvido, is(equalTo(50)));
    }

    @Test
    public void deve_DevolverStargazersCount_QuandoReceberStargazersCount(){
        repositorio.setStargazers_count(50);
        int stargazersCountDevolvido = repositorio.getStargazers_count();

        assertThat(stargazersCountDevolvido, is(equalTo(50)));
    }

}
