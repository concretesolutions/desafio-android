package br.com.desafioandroid.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.com.desafioandroid.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import br.com.desafioandroid.R;
import br.com.desafioandroid.holder.RepositoryHolder;
import br.com.desafioandroid.model.Repository;

public class RepositoryAdapter extends BaseAdapter {
    List<Repository> repositoryList = new ArrayList<>();
    Context context;
    ImageLoader imgLoader;

    public RepositoryAdapter(List<Repository> repositories, Context context) {
        this.repositoryList = repositories;
        this.context = context;
        this.imgLoader = new ImageLoader(context);

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
        final RepositoryHolder holder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_repositories, viewGroup, false);
            holder = new RepositoryHolder();

            holder.setRepositoryName((TextView) view.findViewById(R.id.repositoryName));
            holder.setRepositoryDescription((TextView) view.findViewById(R.id.repositoryDescription));
            holder.setCountForks((TextView) view.findViewById(R.id.countForks));
            holder.setCountStars((TextView) view.findViewById(R.id.countStars));
            holder.setImgUser((ImageView) view.findViewById(R.id.imgUser));
            holder.setUsername((TextView) view.findViewById(R.id.username));
            holder.setFullName((TextView) view.findViewById(R.id.fullName));

            view.setTag(holder);
        } else {
            holder = (RepositoryHolder) view.getTag();
        }

        Repository repo = repositoryList.get(i);

        holder.getRepositoryName().setText(repo.getName());
        holder.getRepositoryDescription().setText(repo.getDescription());
        holder.getCountForks().setText(String.valueOf(repo.getForks()));
        holder.getCountStars().setText(String.valueOf(repo.getStargazers_count()));
        holder.getUsername().setText(repo.getOwner().getLogin());
        holder.getFullName().setText(repo.getFull_name());


        //set img from url

        imgLoader.displayImage(repo.getOwner().getAvatar_url(), holder.getImgUser());


        return view;
    }
}
