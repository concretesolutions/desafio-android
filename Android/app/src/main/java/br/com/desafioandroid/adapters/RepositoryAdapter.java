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

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

import br.com.desafioandroid.R;
import br.com.desafioandroid.holder.RepositoryHolder;
import br.com.desafioandroid.model.Repository;

public class RepositoryAdapter extends BaseAdapter {
    List<Repository> repositoryList = new ArrayList<>();
    Context context;
    ImageLoader imgLoader;

    public RepositoryAdapter(List<Repository> repositories, Context context, ImageLoader imageLoader) {
        this.repositoryList = repositories;
        this.context = context;
        this.imgLoader = imageLoader;
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
            holder.setProgressBar((ProgressBar) view.findViewById(R.id.progressBar));

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
        holder.getUsername().setText(repo.getFull_name());


        //set img from url

        imgLoader.displayImage(repo.getOwner().getAvatar_url(), holder.getImgUser(), null, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                holder.getImgUser().setImageDrawable(context.getResources().getDrawable(R.drawable.default_user));
                holder.getProgressBar().setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                holder.getProgressBar().setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                holder.getProgressBar().setVisibility(View.GONE);
                holder.getImgUser().setImageBitmap(loadedImage);

            }
        });


        return view;
    }
}
