package isaacborges.com.projetoconcrete.model;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AutorTest {

    //Criar cenário de teste
    //Executar ação esperada
    //Testar o resultado esperado

    //[deve][resultado esperado][estado de teste]

    private Autor autor = new Autor();

    @Test
    public void deve_DevolverLogin_QuandoReceberLogin() {

        autor.setLogin("Login");
        String loginDevolvido = autor.getLogin();

        assertThat(loginDevolvido, is(equalTo("Login")));
    }

    @Test
    public void deve_DevolverAvatarUrl_QuandoReceberAvatarUrl(){
        autor.setAvatar_url("https://www.teste.com.br");
        String avatarUrlDevolvida = autor.getAvatar_url();

        assertThat(avatarUrlDevolvida, is(equalTo("https://www.teste.com.br")));
    }

    /*
    @Test
    public void deve_DevolverId_QuandoReceberId(){
        autor.setId(12345);
        long id = autor.getId();

        assertThat(id, is(equalToLongLong(12345)));
    } */


}