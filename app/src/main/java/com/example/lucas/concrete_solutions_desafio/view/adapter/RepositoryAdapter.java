package com.example.lucas.concrete_solutions_desafio.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.lucas.concrete_solutions_desafio.R;
import com.example.lucas.concrete_solutions_desafio.contract.MainContract;
import com.example.lucas.concrete_solutions_desafio.model.Repository;
import com.example.lucas.concrete_solutions_desafio.model.RepositoryList;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolderRepository>{

    private final Context context;
    private RepositoryList repositories;
    private MainContract.View view;

    public RepositoryAdapter(RepositoryList repositories, Context context, MainContract.View view) {
        this.repositories = repositories;
        this.context = context;
        this.view = view;
    }

    @NonNull
    @Override
    public RepositoryAdapter.ViewHolderRepository onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.repository_list, parent, false);
        ViewHolderRepository holderRepository = new ViewHolderRepository(view);

        return holderRepository;
    }

    @Override
    public void onBindViewHolder(@NonNull final RepositoryAdapter.ViewHolderRepository holder, int position) {
        if ((repositories != null) && (repositories.repositoriesCount() > 0)) {
            Repository repository = repositories.getRepositoryByPosition(position);
            repository.setColorDetails(position);
            holder.bind(repository);
        }
    }

    @Override
    public int getItemCount() {
        return repositories.repositoriesCount();
    }

    public class ViewHolderRepository extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        private TextView stars_count;
        private TextView forks_count;
        private TextView owner_login;
        private CircleImageView owner_picture;
        private View vertical_bar;

        ViewHolderRepository(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.mainActivity_tvRepositoryTitle);
            description = itemView.findViewById(R.id.mainActivity_tvRepositoryDescription);
            stars_count = itemView.findViewById(R.id.mainActivity_tvFavoritedCount);
            forks_count = itemView.findViewById(R.id.mainActivity_tvForksCount);
            owner_picture = itemView.findViewById(R.id.mainActivity_ivRepositoryOwnerPicture);
            owner_login = itemView.findViewById(R.id.mainActivity_tvRepositoryOwnerLogin);
            vertical_bar = itemView.findViewById(R.id.mainActivity_vwRepositoryDivider);
        }

        void bind(final Repository repository) {

            Picasso.get().load(repository.getUser().getAvatar_url()).into(owner_picture);
            owner_login.setText(repository.getUser().getLogin());
            title.setText(repository.getName());
            description.setText(repository.getDescription());
            stars_count.setText(repository.getStargazers_count());
            forks_count.setText(repository.getForks_count());
            vertical_bar.setBackgroundResource(repository.getColorDetails());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (view != null) {
                        view.onRepositoryClicked(repository);
                    }
                }
            });


        }
    }

}
