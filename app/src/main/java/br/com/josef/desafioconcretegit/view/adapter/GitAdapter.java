package br.com.josef.desafioconcretegit.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.josef.desafioconcretegit.R;
import br.com.josef.desafioconcretegit.model.pojo.repositories.Item;
import br.com.josef.desafioconcretegit.view.interfaces.GitOnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class GitAdapter extends RecyclerView.Adapter<GitAdapter.ViewHolder> {

    private List<Item> resultList;
    private GitOnClick listener;

    public GitAdapter(List<Item> resultList, GitOnClick listener) {
        this.resultList = resultList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_recycler_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item result = this.resultList.get(position);
        holder.onBind(result);

        holder.itemView.setOnClickListener(v -> {
            listener.OnClick(result);
        });

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public void atualizalista(List<Item> results) {
        if (resultList.isEmpty()) {
            this.resultList = results;
            notifyDataSetChanged();
        } else {
            this.resultList.addAll(results);
        }
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nomeRep;
        private TextView nomeCompleto;
        private TextView nomeLogin;
        private TextView descricaoRepo;
        private TextView numerosForks;
        private TextView numeroStars;
        private CircleImageView fotoPerfil;

        public ViewHolder(View view) {
            super(view);

            nomeRep = view.findViewById(R.id.NomeRepo);
            nomeLogin = view.findViewById(R.id.UserNameLogin);
            nomeCompleto = view.findViewById(R.id.nomeSobrenome);
            descricaoRepo = view.findViewById(R.id.DescriRepo);
            numerosForks = view.findViewById(R.id.ForksRepo);
            numeroStars = view.findViewById(R.id.numeroStars);
            fotoPerfil = view.findViewById(R.id.fotoPerfilCard);

        }

        void onBind(Item result) {
            nomeRep.setText(result.getName());
            nomeLogin.setText(result.getOwner().getLogin());
            nomeCompleto.setText(result.getFullName());
            descricaoRepo.setText(result.getDescription());
            numeroStars.setText(result.getStargazersCount().toString());
            numerosForks.setText(result.getForksCount().toString());


            Picasso.get().load(result.getOwner().getAvatarUrl()).into(fotoPerfil);
        }

    }
}
