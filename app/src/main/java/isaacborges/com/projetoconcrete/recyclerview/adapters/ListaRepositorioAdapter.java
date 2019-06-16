package isaacborges.com.projetoconcrete.recyclerview.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import isaacborges.com.projetoconcrete.R;
import isaacborges.com.projetoconcrete.model.Repositorio;
import isaacborges.com.projetoconcrete.recyclerview.adapters.listeners.OnRepositorioItemClickListener;

public class ListaRepositorioAdapter extends RecyclerView.Adapter<ListaRepositorioAdapter.RepositorioViewHolder> {

    private final Context context;
    private final List<Repositorio> repositorios;
    private OnRepositorioItemClickListener onRepositorioItemClickListener;

    public ListaRepositorioAdapter(Context context) {
        this.context = context;
        this.repositorios = new ArrayList<>();
    }

    public void setOnRepositorioItemClickListener(OnRepositorioItemClickListener onRepositorioItemClickListener) {
        this.onRepositorioItemClickListener = onRepositorioItemClickListener;
    }

    @NonNull
    @Override
    public RepositorioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_repositorio, viewGroup, false);
        return new RepositorioViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositorioViewHolder viewHolder, int position) {
        Repositorio repositorio = repositorios.get(position);
        viewHolder.vincula(repositorio);
    }

    @Override
    public int getItemCount() {
        return repositorios.size();
    }

    public List<Repositorio> getListaRepositorios(){
        return repositorios;
    }

    public void atualiza(List<Repositorio> listaDeRepositorios){
        this.repositorios.addAll(listaDeRepositorios);
        atualizaLista();
    }

    public void atualizaLista() {
        notifyDataSetChanged();
    }

    public void limpaInformacoes(){
        repositorios.clear();
    }

    class RepositorioViewHolder extends RecyclerView.ViewHolder {

        private final TextView nome;
        private final TextView descricao;
        private final TextView numeroDeStars;
        private final TextView numeroDeForks;
        private final TextView nomeAutor;
        private final CircleImageView imgAutor;

        private Repositorio repositorio;

        public RepositorioViewHolder(View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.item_repositorio_txtNome);
            descricao = itemView.findViewById(R.id.item_repositorio_txtDescricao);
            numeroDeForks = itemView.findViewById(R.id.item_repositorio_txtNumeroForks);
            numeroDeStars = itemView.findViewById(R.id.item_repositorio_txtNumeroStars);

            nomeAutor = itemView.findViewById(R.id.item_repositorio_txtNomeAutor);
            imgAutor = itemView.findViewById(R.id.item_repositorio_imgAutor);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRepositorioItemClickListener.onItemClick(repositorio, getAdapterPosition());
                }
            });

        }

        public void vincula(Repositorio repositorio) {
            this.repositorio = repositorio;
            preencheCampo(repositorio);
        }

        private void preencheCampo(Repositorio repositorio) {
            nome.setText(repositorio.getName());
            descricao.setText(repositorio.getDescription());
            numeroDeStars.setText(String.valueOf(repositorio.getStargazers_count()));
            numeroDeForks.setText(String.valueOf(repositorio.getForks_count()));

            nomeAutor.setText(repositorio.getAutor().getLogin());

            Picasso.get().load(repositorio.getAutor().getAvatar_url()).into(imgAutor);
        }

    }

}
