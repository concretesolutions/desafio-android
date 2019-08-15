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
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

import br.com.desafioandroid.R;
import br.com.desafioandroid.holder.PullsHolder;
import br.com.desafioandroid.model.PullsRequests;

public class PullsAdapter extends BaseAdapter {
    List<PullsRequests> pullsRequests = new ArrayList<PullsRequests>();
    Context context;
    ImageLoader imgLoader;

    public PullsAdapter(List<PullsRequests> requests, Context context, ImageLoader imgLoader) {
        this.pullsRequests = requests;
        this.context = context;
        this.imgLoader = imgLoader;
    }

    @Override
    public int getCount() {
        return pullsRequests.size();
    }

    @Override
    public Object getItem(int i) {
        return pullsRequests.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final PullsHolder holder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_pulls, viewGroup, false);
            holder = new PullsHolder();

            holder.setRepositoryName((TextView) view.findViewById(R.id.repositoryName));
            holder.setData((TextView) view.findViewById(R.id.data));
            holder.setRepositoryDescription((TextView) view.findViewById(R.id.repositoryDescription));
            holder.setUsername((TextView) view.findViewById(R.id.username));
            holder.setFullName((TextView) view.findViewById(R.id.fullName));
            holder.setImgUser((ImageView) view.findViewById(R.id.imgUser));
            holder.setProgressBar((ProgressBar) view.findViewById(R.id.progressBar));

            view.setTag(holder);
        } else {
            holder = (PullsHolder) view.getTag();
        }

        PullsRequests pulls = pullsRequests.get(i);

        holder.getRepositoryName().setText(pulls.getTitle());

        String[] dataSplit = pulls.getCreated_at().split("T");
        String data = formatDate(dataSplit[0]);
        holder.getData().setText(data);
        holder.getRepositoryDescription().setText(pulls.getBody());
        holder.getUsername().setText(pulls.getUser().getLogin());


        //setando a imagem:
        imgLoader.displayImage(pulls.getUser().getAvatar_url(), holder.getImgUser(), null, new SimpleImageLoadingListener() {
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

    String formatDate(String data) {
        String[] dataSeparada = data.split("-");
        return dataSeparada[2] + "/" + dataSeparada[1] + "/" + dataSeparada[0];
    }
}
