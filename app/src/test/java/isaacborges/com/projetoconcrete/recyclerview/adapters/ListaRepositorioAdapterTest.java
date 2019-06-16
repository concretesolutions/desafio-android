package isaacborges.com.projetoconcrete.recyclerview.adapters;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import isaacborges.com.projetoconcrete.model.Repositorio;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ListaRepositorioAdapterTest {

    @Mock
    private Context context;
    @Spy
    private ListaRepositorioAdapter adapter = new ListaRepositorioAdapter(context);

    @Test
    public void deve_AtualizarListaDeRepositorios_QuandoReceberListaDeRepositorios(){

        doNothing().when(adapter).atualizaLista();

        adapter.atualiza(new ArrayList<>(Arrays.asList(
                new Repositorio(),
                new Repositorio()
        )));

        int quantidadeLeiloesDevoldida = adapter.getItemCount();

        verify(adapter).atualizaLista();
        assertThat(quantidadeLeiloesDevoldida, is(equalTo(2)));
    }

}