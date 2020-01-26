package br.com.victoramaral.githubdive.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.victoramaral.githubdive.R;
import br.com.victoramaral.githubdive.model.pojos.repositories.Item;
import br.com.victoramaral.githubdive.view.interfaces.RepositoryOnClick;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {

    private List<Item> itens;
    private RepositoryOnClick onClickListener;

    public RepositoryAdapter(List<Item> itens, RepositoryOnClick onClickListener) {
        this.itens = itens;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itens.get(position);
        holder.bind(item);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.repositoryOnClick(item);
            }
        });
    }
    public void setUpdate(List<Item> itens) {
        if (this.itens.isEmpty()) {
            this.itens = itens;
        } else {
            this.itens.addAll(itens);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nome;
        private TextView descricao;
        private TextView forks;
        private TextView stars;
        private TextView usuario;
        private TextView nomeSobrenome;
        private ImageView foto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.textViewRepository);
            descricao = itemView.findViewById(R.id.textViewDescricao);
            forks = itemView.findViewById(R.id.textViewForks);
            stars = itemView.findViewById(R.id.textViewStars);
            usuario = itemView.findViewById(R.id.textViewUsername);
            nomeSobrenome = itemView.findViewById(R.id.textViewNomeSobrenome);
            foto = itemView.findViewById(R.id.imageViewProfile);
        }

        public void bind(Item item) {
            nome.setText(item.getName());
            descricao.setText(item.getDescription());
            forks.setText(item.getForks().toString());
            stars.setText(item.getStargazersCount().toString());
            usuario.setText(item.getOwner().getLogin());
            nomeSobrenome.setText(item.getName());
            Picasso.get().load(item.getOwner().getAvatarUrl() + ".jpg").into(foto);
        }
    }
}
