package br.com.concrete.desafio_android.gui.adapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.concrete.desafio_android.R;
import br.com.concrete.desafio_android.domain.Repository;

public class RepositoryAdapter extends ArrayAdapter<Repository> {

    private List<Repository> repositories;
    private Activity activity;
    private int resource;

    public RepositoryAdapter(@LayoutRes int resource, Activity activity, List<Repository> repositories) {
        super(activity.getApplicationContext(), resource, repositories);
        this.resource = resource;
        this.activity = activity;
        this.repositories = repositories;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = activity.getLayoutInflater().from(activity.getApplicationContext()).inflate(this.resource, null);
        }

        Repository repository = repositories.get(position);
        TextView repositoryName = convertView.findViewById(R.id.repository_name);
        TextView repositoryDescription = convertView.findViewById(R.id.repository_description);
        TextView forkValue = convertView.findViewById(R.id.fork_value);
        TextView starValue = convertView.findViewById(R.id.star_value);
        TextView ownerName = convertView.findViewById(R.id.repository_ownername);
        TextView fullName = convertView.findViewById(R.id.repository_full_name);

        ImageView imageView = convertView.findViewById(R.id.repository_owner_image);

        repositoryName.setText(repository.getName());
        repositoryDescription.setText(repository.getDescription());
        forkValue.setText(Integer.toString(repository.getForks_count()));
        starValue.setText(Integer.toString(repository.getStargazers_count()));
        ownerName.setText(repository.getName());
        fullName.setText(repository.getFull_name());

        //Picasso already cache the image and try to load it from cache, if it's not found it goes through the web
        Picasso
                .get()
                .load(repository.getOwner().getAvatar_url())
                .error(R.mipmap.ic_launcher)
                .into(imageView);

        return convertView;
    }
}
