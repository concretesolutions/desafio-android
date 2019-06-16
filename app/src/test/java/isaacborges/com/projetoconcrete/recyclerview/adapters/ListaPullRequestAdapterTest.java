package isaacborges.com.projetoconcrete.recyclerview.adapters;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import isaacborges.com.projetoconcrete.model.PullRequest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ListaPullRequestAdapterTest {

    @Mock
    private Context context;
    @Spy
    private ListaPullRequestAdapter adapter = new ListaPullRequestAdapter(context);

    @Test
    public void deve_AtualizarListaDePullRequests_QuandoReceberListaDePullRequests(){

        doNothing().when(adapter).atualizaLista();

        adapter.atualiza(new ArrayList<>(Arrays.asList(
            new PullRequest(),
            new PullRequest()
        )));

        int quantidadePullRequestsDevolvida = adapter.getItemCount();

        verify(adapter).atualizaLista();
        assertThat(quantidadePullRequestsDevolvida, is(equalTo(2)));
    }

}