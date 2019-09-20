package br.com.concrete.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import br.com.concrete.R;
import br.com.concrete.model.RetornoPullRequest;
import br.com.concrete.util.Utils;

public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.PullRequestViewHolder> {

    private Activity context;
    private List<RetornoPullRequest> lista;
    private ImageLoadingListener animateFirstListener = new PullRequestAdapter.AnimateFirstDisplayListener();

    public PullRequestAdapter(Activity context, List<RetornoPullRequest> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public PullRequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PullRequestViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_pull_request, parent, false));
    }

    @Override
    public void onBindViewHolder(final PullRequestViewHolder holder, final int position) {
        final RetornoPullRequest pullRequest = lista.get(position);
        if (position % 2 == 1) {
            holder.view.setBackgroundColor(context.getResources().getColor(R.color.white));
        } else {
            holder.view.setBackgroundColor(context.getResources().getColor(R.color.adapter_background));
        }
        holder.setIsRecyclable(false);
        holder.titulo.setText(pullRequest.getTitle());
        holder.body.setText(pullRequest.getBody());
        holder.username.setText(pullRequest.getUser().getLogin());
        holder.nome.setText(new StringBuffer(pullRequest.getUser().getLogin()).reverse());
        ImageLoader.getInstance().displayImage(pullRequest.getUser().getAvatar_url(), holder.foto_user, Utils.getOptions(), animateFirstListener);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.callSite(context, pullRequest.getUser().getHtml_url());
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class PullRequestViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView titulo, body, username, nome;
        ImageView foto_user;

        public PullRequestViewHolder(View view) {
            super(view);
            this.view = view;
            titulo = this.view.findViewById(R.id.titulo);
            body = this.view.findViewById(R.id.body);
            username = this.view.findViewById(R.id.username);
            nome = this.view.findViewById(R.id.nome);
            foto_user = this.view.findViewById(R.id.foto_user);
        }
    }

    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }
}