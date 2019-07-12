package com.felipe.palma.desafioconcrete.ui.repository;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.felipe.palma.desafioconcrete.R;
import com.felipe.palma.desafioconcrete.domain.model.Item;
import com.felipe.palma.desafioconcrete.domain.response.RepositoriesResponse;
import com.felipe.palma.desafioconcrete.network.IServiceGithub;
import com.felipe.palma.desafioconcrete.network.ServiceGithubImp;
import com.felipe.palma.desafioconcrete.utils.Config;

import java.util.List;


/**
 * Created by Felipe Palma on 11/07/2019.
 */

public class RepositoriesActivity extends AppCompatActivity implements RepositoriesContract.View {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showRepositories(List<Item> itens) {

    }
}
