package isaacborges.com.projetoconcrete.model;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PullRequestTest {

    //[deve][resultado esperado][estado de teste]

    private PullRequest pullRequest = new PullRequest();

    @Test
    public void deve_DevolverTitle_QuandoReceberTitle(){
        pullRequest.setTitle("Título");
        String titleDevolvido = pullRequest.getTitle();
        assertThat(titleDevolvido, is(equalTo("Título")));
    }

    @Test
    public void deve_DevolverUser_QuandoReceberUser(){
        Autor autor = new Autor();
        autor.setId(1);
        autor.setLogin("isaacborges92");

        pullRequest.setUser(autor);
        Autor userDevolvido = pullRequest.getUser();

        assertThat(userDevolvido, is(equalTo(autor)));
    }

    @Test
    public void deve_DevolverBody_QuandoReceberBody(){
        pullRequest.setBody("Body");
        String bodyDevolvido = pullRequest.getBody();
        assertThat(bodyDevolvido, is(equalTo("Body")));
    }

    @Test
    public void deve_DevolverHtmlUrl_QuandoReceberHtmlUrl(){
        pullRequest.setHtml_url("https://www.teste.com.br");
        String htmlUrlDevolvida = pullRequest.getHtml_url();
        assertThat(htmlUrlDevolvida, is(equalTo("https://www.teste.com.br")));
    }

    @Test
    public void deve_DevolverCreatedAt_QuandoReceberCreatedAt(){
        pullRequest.setCreated_at("15/06/2019");
        String createdAtDevolvida = pullRequest.getCreated_at();
        assertThat(createdAtDevolvida, is(equalTo("15/06/2019")));
    }

}
