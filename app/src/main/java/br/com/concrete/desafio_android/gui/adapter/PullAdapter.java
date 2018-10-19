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

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.concrete.desafio_android.R;
import br.com.concrete.desafio_android.domain.Pull;
import br.com.concrete.desafio_android.domain.User;

public class PullAdapter extends ArrayAdapter<Pull> {

    private List<Pull> pullRequests;
    private Activity activity;
    private int resource;

    public PullAdapter(@LayoutRes int resource, Activity activity, List<Pull> pullRequests) {
        super(activity.getApplicationContext(), resource, pullRequests);
        this.resource = resource;
        this.activity = activity;
        this.pullRequests = pullRequests;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = activity.getLayoutInflater().from(activity.getApplicationContext()).inflate(this.resource, null);
        }

        Pull pullRequest = pullRequests.get(position);

        TextView title = convertView.findViewById(R.id.pull_request_title);
        TextView description = convertView.findViewById(R.id.pull_request_description);
        TextView username = convertView.findViewById(R.id.pull_request_username);
        TextView date = convertView.findViewById(R.id.pull_request_date);

        title.setText(pullRequest.getTitle());
        description.setText(pullRequest.getBody());
        username.setText(pullRequest.getUser().getLogin());
        date.setText(pullRequest.getCreated_at());

        ImageView imageView = convertView.findViewById(R.id.pull_owner_image);

        //Picasso already cache the image and try to load it from cache, if it's not found it goes through the web
        Picasso
                .get()
                .load(pullRequest.getUser().getAvatar_url())
                .error(R.mipmap.ic_launcher)
                .into(imageView);

        return convertView;
    }

}
