package com.alexandreobs.testeconcrete.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alexandreobs.testeconcrete.R;
import com.alexandreobs.testeconcrete.model.pojo.repositories.Item;
import com.alexandreobs.testeconcrete.view.interfaces.OnClickRepository;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GitAdapter extends RecyclerView.Adapter<GitAdapter.ViewHolder> {

    private List<Item> resultList;
    private OnClickRepository listener;

    public GitAdapter(List<Item> resultList, OnClickRepository listener) {
        this.resultList = resultList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_projects, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item result = this.resultList.get(position);
        holder.onBind(result);

        holder.itemView.setOnClickListener(v ->{
            listener.OnClick(result);
        });

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public void update(List<Item> results) {
        if (this.resultList.isEmpty()){
            this.resultList = results;
        } else {
            this.resultList.addAll(results);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        this.resultList.clear();
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nomeRep;
        private TextView nomeFull;
        private TextView nomeLogin;
        private TextView descricaoRepo;
        private TextView numerosForks;
        private TextView numeroStars;
        private CircleImageView fotoPerfil;

        public ViewHolder(View view) {
            super(view);

            nomeRep = view.findViewById(R.id.NomeRepo);
            nomeLogin = view.findViewById(R.id.UserNameLogin);
            nomeFull = view.findViewById(R.id.nomeCompleto);
            descricaoRepo = view.findViewById(R.id.DescriRepo);
            numerosForks = view.findViewById(R.id.ForksRepo);
            numeroStars = view.findViewById(R.id.numeroStars);
            fotoPerfil = view.findViewById(R.id.fotoPerfilCard);

        }

        void onBind(Item result) {
            nomeRep.setText(result.getName());
            nomeLogin.setText(result.getOwner().getLogin());
            nomeFull.setText(result.getFullName());
            descricaoRepo.setText(result.getDescription());
            numeroStars.setText(result.getStargazersCount().toString());
            numerosForks.setText(result.getForksCount().toString());


            Picasso.get().load(result.getOwner().getAvatarUrl()).into(fotoPerfil);
        }

    }
}
