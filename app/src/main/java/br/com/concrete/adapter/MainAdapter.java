package br.com.concrete.adapter;

import android.content.Context;
import android.content.Intent;
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
import br.com.concrete.activity.DetalheActivity;
import br.com.concrete.model.RetornoRepositorio;
import br.com.concrete.util.Utils;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private Context context;
    private List<RetornoRepositorio.Repositorio> lista;
    private ImageLoadingListener animateFirstListener = new MainAdapter.AnimateFirstDisplayListener();

    public MainAdapter(Context context, List<RetornoRepositorio.Repositorio> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_main, parent, false));
    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder, final int position) {
        final RetornoRepositorio.Repositorio repo = lista.get(position);
        if (position % 2 == 1) {
            holder.view.setBackgroundColor(context.getResources().getColor(R.color.white));
        } else {
            holder.view.setBackgroundColor(context.getResources().getColor(R.color.adapter_background));
        }
        holder.setIsRecyclable(false);
        holder.nome_repo.setText(repo.getName());
        holder.descricao.setText(repo.getDescription());
        holder.num_forks.setText(""+repo.getForks_count());
        holder.num_star.setText(""+repo.getStargazers_count());
        ImageLoader.getInstance().displayImage(repo.getOwner().getAvatar_url(), holder.foto, Utils.getOptions(), animateFirstListener);
        holder.nome.setText(repo.getOwner().getLogin());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDetalhamento(repo);
            }
        });
    }

    private void callDetalhamento(RetornoRepositorio.Repositorio repo){
        Intent intent = new Intent(context, DetalheActivity.class);
        intent.putExtra("name",repo.getName());
        intent.putExtra("login",repo.getOwner().getLogin());
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView nome_repo, descricao, num_forks, num_star, nome;
        ImageView foto;

        public MainViewHolder(View view) {
            super(view);
            this.view = view;
            nome_repo = this.view.findViewById(R.id.nome_repo);
            descricao = this.view.findViewById(R.id.descricao);
            num_forks = this.view.findViewById(R.id.num_forks);
            num_star = this.view.findViewById(R.id.num_star);
            nome = this.view.findViewById(R.id.nome);
            foto = this.view.findViewById(R.id.foto);
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