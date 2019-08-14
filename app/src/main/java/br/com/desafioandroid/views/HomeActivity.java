package br.com.desafioandroid.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import br.com.desafioandroid.R;

public class HomeActivity extends AppCompatActivity {

    ListView listRepositorios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Github JavaPop");

        listRepositorios = (ListView) findViewById(R.id.listRepositorios);



    }
}
