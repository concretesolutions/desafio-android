package br.com.devdiegopirutti.mainactivity.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import br.com.devdiegopirutti.mainactivity.PullRepository.PullMain;
import br.com.devdiegopirutti.mainactivity.R;
import br.com.devdiegopirutti.mainactivity.models.ItemsItem;
import br.com.devdiegopirutti.mainactivity.ui.MainActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private ArrayList<ItemsItem> arrayList;

    public UserAdapter() {
        arrayList = new ArrayList<>();
    }

    public void adicionarLista(List<ItemsItem> lista) {
        arrayList.addAll(lista);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.api_model, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nomeRepositorio;
        private TextView descricaoRepositorio;
        private TextView userName;
        private TextView autorName;
        private CircleImageView autorFoto;
        private TextView numStars;
        private TextView numFork;

        ViewHolder(@NonNull final View itemView) {
            super(itemView);

            nomeRepositorio = itemView.findViewById(R.id.nome_repositorio);
            descricaoRepositorio = itemView.findViewById(R.id.descricao_rep);
            userName = itemView.findViewById(R.id.user_name);
            autorName = itemView.findViewById(R.id.nome_autor);
            autorFoto = itemView.findViewById(R.id.image_usuario);
            numStars = itemView.findViewById(R.id.number_star);
            numFork = itemView.findViewById(R.id.numberlink);
        }

        void bind(final ItemsItem item) {
            nomeRepositorio.setText(item.getName());
            descricaoRepositorio.setText(item.getDescription());
            autorName.setText(item.getFullName());
            userName.setText(item.getOwner().getLogin());
            numFork.setText(String.valueOf(item.getForks()));
            numStars.setText(String.valueOf(item.getStargazersCount()));


            Picasso.get()
                    .load(item.getOwner().getAvatarUrl())
                    .into(autorFoto);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), PullMain.class);
                    intent.putExtra("REPOSITORIO", item.getName());
                    intent.putExtra("LOGIN", item.getOwner().getLogin());
                    ((MainActivity) itemView.getContext()).startActivityForResult(intent, 0);
                }
            });
        }
    }

}
