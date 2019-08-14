package br.com.desafioandroid.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.desafioandroid.model.Repositorio;

public class RepositoriosAdapter extends BaseAdapter {
    List<Repositorio> repositorioList = new ArrayList<>();

    public RepositoriosAdapter(List<Repositorio> repositorios) {
        this.repositorioList = repositorios;
    }


    @Override
    public int getCount() {
        return repositorioList.size();
    }

    @Override
    public Object getItem(int i) {
        return repositorioList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        


        return view;
    }
}
