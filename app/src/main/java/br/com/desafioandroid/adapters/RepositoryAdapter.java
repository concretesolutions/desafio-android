package br.com.desafioandroid.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.desafioandroid.model.Repository;

public class RepositoryAdapter extends BaseAdapter {
    List<Repository> repositoryList = new ArrayList<>();

    public RepositoryAdapter(List<Repository> repositories) {
        this.repositoryList = repositories;
    }


    @Override
    public int getCount() {
        return repositoryList.size();
    }

    @Override
    public Object getItem(int i) {
        return repositoryList.get(i);
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
