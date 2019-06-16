package br.com.concrete.desafio.until;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.concrete.desafio.PullRequestActivity;
import br.com.concrete.desafio.R;
import br.com.concrete.desafio.model.Repository;

public class RepositoryAdapter extends GenericAdapter<Repository> {

    public RepositoryAdapter(Repository[] dataSet) {
        super(dataSet);
    }

    @NonNull
    @Override
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.rv_element_repository, viewGroup, false);

        return new GenericViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GenericViewHolder viewHolder, int i) {
        final ConstraintLayout constraintLayout = viewHolder.constraintLayout;
        final Context context = constraintLayout.getContext();

        final TextView tvOwner = constraintLayout.findViewById(R.id.tvOwner);
        final TextView tvName = constraintLayout.findViewById(R.id.tvName);
        final TextView tvDescription = constraintLayout.findViewById(R.id.tvDescription);
        final TextView tvStars = constraintLayout.findViewById(R.id.tvStars);
        final TextView tvForks = constraintLayout.findViewById(R.id.tvForks);
        final ImageView ivPhoto = constraintLayout.findViewById(R.id.ivPhoto);

        if (dataSet.size() == 0) return;

        tvOwner.setText(dataSet.get(i).getOwner().getLogin());
        tvName.setText(dataSet.get(i).getName());
        tvStars.setText(String.valueOf(dataSet.get(i).getStargazers_count()));
        tvForks.setText(String.valueOf(dataSet.get(i).getForks_count()));

        String description = dataSet.get(i).getDescription();
        if (description.length() > 0) tvDescription.setText(description);
        else tvDescription.setText(context.getString(R.string.no_description));

        String photoUrl = dataSet.get(i).getOwner().getAvatar_url() + "&s=100";
        Picasso.get().load(photoUrl).into(ivPhoto);

        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pullUrl = dataSet.get(viewHolder.getAdapterPosition()).getPulls_url();
                pullUrl = pullUrl.substring(0, pullUrl.lastIndexOf("/pulls") + 1);

                String name = dataSet.get(viewHolder.getAdapterPosition()).getName();

                Intent intent = new Intent(context, PullRequestActivity.class);
                intent.putExtra("NAME", name);
                intent.putExtra("URL", pullUrl);
                context.startActivity(intent);
            }
        });
    }
}
