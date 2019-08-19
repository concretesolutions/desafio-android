package br.com.erico.desafio_android.controls.structures;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.erico.desafio_android.R;
import br.com.erico.desafio_android.models.Repository;

public class RepoStructure extends RecyclerView.Adapter<RepoStructure.ViewHolder> {

    private List<Repository> repositories;
    private Context context;
    private RepositoryListener repositoryListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtNameRepository;
        public TextView txtDescriRepository;
        public TextView txtForksCount;
        public TextView txtStarsCount;
        public TextView txtNameAuth;
        public ImageView imageAuth;

        RepositoryListener repositoryListener;

        public ViewHolder(View itemView, RepositoryListener repositoryListener) {
            super(itemView);

            txtNameRepository = itemView.findViewById(R.id.txtNameRepository);
            txtDescriRepository = itemView.findViewById(R.id.txtDescriRepository);
            txtForksCount = itemView.findViewById(R.id.txtForksCount);
            txtStarsCount = itemView.findViewById(R.id.txtStarsCount);
            txtNameAuth = itemView.findViewById(R.id.txtNomeAuth);
            imageAuth = itemView.findViewById(R.id.imageAuth);

            this.repositoryListener = repositoryListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Repository repository = getRepository(getAdapterPosition());
            this.repositoryListener.onRepositoryClick(repository.getOwner().getUsername(), repository.getName());

            notifyDataSetChanged();
        }
    }

    public RepoStructure(Context context, List<Repository> repositories, RepositoryListener repositoryListener) {
        this.repositories = repositories;
        this.context = context;
        this.repositoryListener = repositoryListener;
    }

    @Override
    public RepoStructure.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View repositoryView = inflater.inflate(R.layout.fragment_repositories, parent, false);
        ViewHolder viewHolder = new ViewHolder(repositoryView, this.repositoryListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Repository repository = repositories.get(position);
        TextView txtNameRepository = holder.txtNameRepository;
        TextView txtDescRepository = holder.txtDescriRepository;
        TextView txtForksCount = holder.txtForksCount;
        TextView txtStarsCount = holder.txtStarsCount;
        TextView txtNameAuth = holder.txtNameAuth;
        ImageView imageAuth = holder.imageAuth;

        txtNameRepository.setText(repository.getName());
        txtDescRepository.setText(repository.getDescription());
        txtForksCount.setText(String.valueOf(repository.getNumForks()));
        txtStarsCount.setText(String.valueOf(repository.getStarsCount()));
        txtNameAuth.setText(repository.getOwner().getUsername());

        Picasso.with(context)
                .load(repository.getOwner()
                        .getPhoto())
                .resize(160, 160)
                .into(imageAuth);
    }

    @Override
    public int getItemCount() {
        return repositories == null ? 0 : repositories.size();
    }

    public void updateRepositories(List<Repository> repositories) {
        this.repositories = repositories;
        notifyDataSetChanged();
    }

    public Repository getRepository(int adapterPosition) {
        return repositories.get(adapterPosition);
    }

    public interface RepositoryListener {
        void onRepositoryClick(String authorName, String repoName);
    }

    public void add(Repository repo) {
        repositories.add(repo);
        notifyItemInserted(repositories.size() - 1);
    }

    public void addAll(List<Repository> repositories) {
        for (Repository repo : repositories) {
            add(repo);
        }
    }

    public List<Repository> getRepositories() {
        return repositories;
    }
}
