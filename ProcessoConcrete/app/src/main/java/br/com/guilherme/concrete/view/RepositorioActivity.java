package br.com.guilherme.concrete.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import br.com.guilherme.concrete.R;

public class RepositorioActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositorio);

        toolbar = findViewById(R.id.toolbar);

        String nomeRepositorio = getIntent().getStringExtra("nomeRepositorio") != null
                ? getIntent().getStringExtra("nomeRepositorio")
                : getResources().getString(R.string.title_padrao_reqeuests);
        toolbar.setTitle(nomeRepositorio);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, RepositorioActivity.class);
    }
}
