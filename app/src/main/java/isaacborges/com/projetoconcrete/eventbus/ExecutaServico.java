package isaacborges.com.projetoconcrete.eventbus;

import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import isaacborges.com.projetoconcrete.dto.PullRequestSync;
import isaacborges.com.projetoconcrete.dto.RepositoriosSync;
import isaacborges.com.projetoconcrete.eventbus.events.BuscaListaPullRequestsEvent;
import isaacborges.com.projetoconcrete.eventbus.events.BuscaListaRepositoriosEvent;
import isaacborges.com.projetoconcrete.model.PullRequest;
import isaacborges.com.projetoconcrete.model.Repositorio;
import isaacborges.com.projetoconcrete.preferences.BuscaPreferences;
import isaacborges.com.projetoconcrete.retrofit.RetrofitInicializador;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExecutaServico {

    private final EventBus eventBus = EventBus.getDefault();
    private final Context context;
    private final BuscaPreferences buscaPreferences;

    public ExecutaServico(Context context){
        this.context = context;
        this.buscaPreferences = new BuscaPreferences(context);
    }

    public void buscaRepositorios(int numeroDaPagina, final ErroAoCarregarInformacoes listener){

        String ordemBusca = buscaPreferences.getOrdemBusca();
        String linguagemBusca = buscaPreferences.getLinguagemBusca();

        Call<RepositoriosSync> call = new RetrofitInicializador().getRepositorioService().buscaRepositorios(linguagemBusca, ordemBusca, numeroDaPagina);
        call.enqueue(new Callback<RepositoriosSync>() {
            @Override
            public void onResponse(Call<RepositoriosSync> call, Response<RepositoriosSync> response) {
                RepositoriosSync repositoriosSync = response.body();
                List<Repositorio> repositorios = repositoriosSync.getListaDeRepositorios();
                eventBus.post(new BuscaListaRepositoriosEvent(repositorios));
            }

            @Override
            public void onFailure(Call<RepositoriosSync> call, Throwable t) {
                listener.erroAoCarregar("Não foi possível encontrar os resultados.");
                Log.e("erroRetrofit", t.getMessage());
            }
        });
    }

    public void buscaPullRequests(String nomeAutor, String nomeRepositorio, final ErroAoCarregarInformacoes listener){

        Call<PullRequestSync> call = new RetrofitInicializador().getRepositorioService().buscaPullRequestsDoRepositorio(nomeAutor, nomeRepositorio);
        call.enqueue(new Callback<PullRequestSync>() {
            @Override
            public void onResponse(Call<PullRequestSync> call, Response<PullRequestSync> response) {
                PullRequestSync pullRequestSync = response.body();
                List<PullRequest> pullRequests = pullRequestSync.getListaDePullRequests();
                eventBus.post(new BuscaListaPullRequestsEvent(pullRequests));
            }

            @Override
            public void onFailure(Call<PullRequestSync> call, Throwable t) {
                listener.erroAoCarregar("Não foi possível encontrar os resultados.");
                Log.e("erroRetrofit", t.getMessage());
            }
        });
    }

    public interface ErroAoCarregarInformacoes{
        void erroAoCarregar(String mensagem);
    }

}
