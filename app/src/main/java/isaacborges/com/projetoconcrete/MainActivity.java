package isaacborges.com.projetoconcrete;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import isaacborges.com.projetoconcrete.dialog.FomularioFiltros;
import isaacborges.com.projetoconcrete.eventbus.ExecutaServico;
import isaacborges.com.projetoconcrete.eventbus.events.BuscaListaRepositoriosEvent;
import isaacborges.com.projetoconcrete.model.Repositorio;
import isaacborges.com.projetoconcrete.recyclerview.EndlessRecyclerOnScrollListener;
import isaacborges.com.projetoconcrete.recyclerview.adapters.ListaRepositorioAdapter;
import isaacborges.com.projetoconcrete.recyclerview.adapters.listeners.OnRepositorioItemClickListener;
import isaacborges.com.projetoconcrete.utils.ConversorJson;

import static isaacborges.com.projetoconcrete.ConstantesDaAplicacao.CHAVE_NUMERODAPAGINA;
import static isaacborges.com.projetoconcrete.ConstantesDaAplicacao.CHAVE_REPOSITORIO;
import static isaacborges.com.projetoconcrete.ConstantesDaAplicacao.NUMERO_INICIAL_DA_PAGINA;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewRepositorio;
    private ProgressBar progressBar;

    private ListaRepositorioAdapter adapter;

    private EventBus eventBus = EventBus.getDefault();

    private final ExecutaServico executaServico = new ExecutaServico(this);

    private int numeroDaPagina = NUMERO_INICIAL_DA_PAGINA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buscaReferenciasDasViews();
        configuraAdapter();
        configuraRecyclerView();

        if (savedInstanceState != null) {
            buscaDadosDoBundle(savedInstanceState);
        } else {
            executaServico.buscaRepositorios(numeroDaPagina, getListener());
        }
    }

    private ExecutaServico.ErroAoCarregarInformacoes getListener() {
        return new ExecutaServico.ErroAoCarregarInformacoes() {
            @Override
            public void erroAoCarregar(String mensagem) {
                defineVisibilidadeProgressBar();
                Toast.makeText(MainActivity.this, mensagem, Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void buscaReferenciasDasViews() {
        recyclerViewRepositorio = findViewById(R.id.recyclerViewRepositorios);
        progressBar = findViewById(R.id.item_progress_bar);
    }

    private void buscaDadosDoBundle(Bundle savedInstanceState) {
        String jsonInString = savedInstanceState.getString(CHAVE_REPOSITORIO);
        List<Repositorio> listaDeRepositorios = (List<Repositorio>) ConversorJson.converteJsonParaLista(jsonInString, Repositorio.class);
        adapter.atualiza(listaDeRepositorios);
        numeroDaPagina = savedInstanceState.getInt(CHAVE_NUMERODAPAGINA);
        defineVisibilidadeProgressBar();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void atualizaListaAlunoEvent(BuscaListaRepositoriosEvent event) {
        numeroDaPagina++;
        adapter.atualiza(event.getRepositorios());
        defineVisibilidadeProgressBar();
    }

    @Override
    public void onStart() {
        super.onStart();
        eventBus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }

    private void defineVisibilidadeProgressBar(){
        if (progressBar.getVisibility() == View.VISIBLE) {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void configuraRecyclerView() {
        recyclerViewRepositorio.setAdapter(adapter);
        recyclerViewRepositorio.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                progressBar.setVisibility(View.VISIBLE);
                executaServico.buscaRepositorios(numeroDaPagina, getListener());
            }
        });
    }

    private void configuraAdapter() {
        adapter = new ListaRepositorioAdapter(this);
        adapter.setOnRepositorioItemClickListener(new OnRepositorioItemClickListener() {
            @Override
            public void onItemClick(Repositorio repositorio, int posicao) {
                vaiParaPullRequestActivity(repositorio);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_main_activity_filtro){
            exibeFormularioDeFiltro();
        }

        return super.onOptionsItemSelected(item);
    }

    private void exibeFormularioDeFiltro() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        FomularioFiltros formulario = new FomularioFiltros(this, viewGroup, new FomularioFiltros.RespostaPositivaDoDialog() {
            @Override
            public void realizaNovaBusca() {
                adapter.limpaInformacoes();
                numeroDaPagina = NUMERO_INICIAL_DA_PAGINA;
                executaServico.buscaRepositorios(numeroDaPagina, getListener());
            }
        });
        formulario.chamaDialog();
    }

    private void vaiParaPullRequestActivity(Repositorio repositorio) {
        Intent intent = new Intent(MainActivity.this, PullRequestActivity.class);
        intent.putExtra(CHAVE_REPOSITORIO, repositorio);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        String jsonString = ConversorJson.converteListaParaJson(adapter.getListaRepositorios());
        outState.putString(CHAVE_REPOSITORIO, jsonString);
        outState.putInt(CHAVE_NUMERODAPAGINA, numeroDaPagina);
        super.onSaveInstanceState(outState);
    }
}
