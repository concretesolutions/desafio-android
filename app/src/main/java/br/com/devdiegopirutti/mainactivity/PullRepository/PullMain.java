package br.com.devdiegopirutti.mainactivity.PullRepository;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import br.com.devdiegopirutti.mainactivity.PullRepository.Pull_Models.PullAdapter;
import br.com.devdiegopirutti.mainactivity.R;
import br.com.devdiegopirutti.mainactivity.models.BasePRResponse;
import br.com.devdiegopirutti.mainactivity.presenter.PullInterface;
import br.com.devdiegopirutti.mainactivity.presenter.PullPresenter;

public class PullMain extends AppCompatActivity implements PullInterface {

    private PullPresenter pullPresenter = new PullPresenter(this);
    private PullAdapter pullAdapter =  new PullAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.e("LOGIN", getIntent().getExtras().getString("LOGIN"));

        Bundle bundle = getIntent().getExtras();
        String nomeRepositorio = bundle.getString("REPOSITORIO");
        setTitle(nomeRepositorio);

        pullPresenter.pegarDados(getIntent().getExtras().getString("LOGIN"), getIntent().getExtras().getString("REPOSITORIO"));
        setupView();
    }

    private void setupView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = findViewById(R.id.pull_recycler);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(pullAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void apresentarDados(List<BasePRResponse> list) {
        pullAdapter.adicionarLista(list);
    }

    @Override
    public void aconteceuUmErro() {
        Toast.makeText(this, "Deu um erroooooo", Toast.LENGTH_LONG).show();

    }
}
