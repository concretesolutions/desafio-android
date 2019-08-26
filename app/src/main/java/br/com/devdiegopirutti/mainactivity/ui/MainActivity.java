package br.com.devdiegopirutti.mainactivity.ui;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import br.com.devdiegopirutti.mainactivity.R;
import br.com.devdiegopirutti.mainactivity.adapter.UserAdapter;
import br.com.devdiegopirutti.mainactivity.helper.EndlessRecyclerViewScrollListener;
import br.com.devdiegopirutti.mainactivity.models.ItemsItem;
import br.com.devdiegopirutti.mainactivity.presenter.MainActivityInterface;
import br.com.devdiegopirutti.mainactivity.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements MainActivityInterface {

    private MainActivityPresenter presenter = new MainActivityPresenter(this);
    private UserAdapter userAdapter = new UserAdapter();
    private int pageNumber = 1;
    private EndlessRecyclerViewScrollListener scrollListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();


        presenter.pegarDados(1);
    }

    private void setupView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = findViewById(R.id.repo_recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(userAdapter);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.pegarDados(pageNumber);
            }
        };

        recyclerView.addOnScrollListener(scrollListener);
    }

    @Override
    public void apresentarDados(List<ItemsItem> list) {
        pageNumber++;
        userAdapter.adicionarLista(list);
    }

    @Override
    public void aconteceuUmErro() {
        Toast.makeText(this, "Deu um erro meu aliado", Toast.LENGTH_LONG).show();
    }
}

