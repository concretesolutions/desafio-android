package isaacborges.com.projetoconcrete;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import isaacborges.com.projetoconcrete.eventbus.ExecutaServico;
import isaacborges.com.projetoconcrete.eventbus.events.BuscaListaPullRequestsEvent;
import isaacborges.com.projetoconcrete.model.PullRequest;
import isaacborges.com.projetoconcrete.model.Repositorio;
import isaacborges.com.projetoconcrete.recyclerview.adapters.ListaPullRequestAdapter;
import isaacborges.com.projetoconcrete.recyclerview.adapters.listeners.OnPullRequestItemClickListener;
import isaacborges.com.projetoconcrete.utils.ConversorJson;

import static isaacborges.com.projetoconcrete.ConstantesDaAplicacao.CHAVE_PULLREQUESTS;
import static isaacborges.com.projetoconcrete.ConstantesDaAplicacao.CHAVE_REPOSITORIO;

public class PullRequestActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPullRequests;

    private ListaPullRequestAdapter adapter;
    private Repositorio repositorio;

    private final EventBus eventBus = EventBus.getDefault();;
    private final ExecutaServico executaServico = new ExecutaServico(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buscaReferenciasDasViews();
        configuraAdapter();
        configuraRecyclerView();
        carregaRepositorioRecebido();

        if(savedInstanceState != null){
            buscaDadosDoBundle(savedInstanceState);
        }else{
            buscaPullRequests();

        }
    }

    private void configuraRecyclerView() {
        recyclerViewPullRequests.setAdapter(adapter);
    }

    private void buscaDadosDoBundle(Bundle savedInstanceState) {
        String jsonInString = savedInstanceState.getString(CHAVE_PULLREQUESTS);
        List<PullRequest> listaPullRequests = (List<PullRequest>) ConversorJson.converteJsonParaLista(jsonInString, PullRequest.class);
        adapter.atualiza(listaPullRequests);
    }

    private void buscaPullRequests() {
        String nomeAutor = repositorio.getAutor().getLogin();
        String nomeRepositorio = repositorio.getName();
        executaServico.buscaPullRequests(nomeAutor, nomeRepositorio, new ExecutaServico.ErroAoCarregarInformacoes() {
            @Override
            public void erroAoCarregar(String mensagem) {
                Toast.makeText(PullRequestActivity.this, mensagem, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void atualizaListaAlunoEvent(BuscaListaPullRequestsEvent event){
        adapter.atualiza(event.getPullRequests());
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

    private void carregaRepositorioRecebido() {
        Intent intent = getIntent();
        if(intent.hasExtra(CHAVE_REPOSITORIO)){
            repositorio = intent.getParcelableExtra(CHAVE_REPOSITORIO);
            setTitle(repositorio.getName());
        }
    }

    private void buscaReferenciasDasViews() {
        recyclerViewPullRequests = findViewById(R.id.recyclerViewPullRequest);
    }

    private void configuraAdapter() {
        adapter = new ListaPullRequestAdapter(this);
        adapter.setOnPullRequestItemClickListener(new OnPullRequestItemClickListener() {
            @Override
            public void onItemClick(PullRequest pullRequest, int posicao) {
                abreSiteDoPullRequest(pullRequest);
            }
        });
    }

    private void abreSiteDoPullRequest(PullRequest pullRequest) {
        Intent intentSite = new Intent(Intent.ACTION_VIEW); //Intent implícita
        intentSite.setData(Uri.parse(pullRequest.getHtml_url()));
        startActivity(intentSite);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        String jsonString = ConversorJson.converteListaParaJson(adapter.getListaPullRequests());
        outState.putString(CHAVE_PULLREQUESTS, jsonString);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
