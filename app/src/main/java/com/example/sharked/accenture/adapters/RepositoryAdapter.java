package com.example.sharked.accenture.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sharked.accenture.R;
import com.example.sharked.accenture.models.Repository;
import com.example.sharked.accenture.views.activities.PullRequestListActivity;
import com.example.sharked.accenture.views.activities.PullRequestListActivity_;

import java.io.Serializable;


public class RepositoryAdapter extends ArrayAdapter<Repository> {
    private Context ctx;
    public RepositoryAdapter(@NonNull Context context, @NonNull Repository[] objects) {
        super(context, R.layout.li_repository, objects);
        ctx = context;

    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = (convertView != null)? convertView :inflater.inflate(R.layout.li_repository, parent, false);

        final Repository item = getItem(position);

        ((TextView) view.findViewById(R.id.repository_name_text)).setText(item.getName());
        ((TextView) view.findViewById(R.id.repository_description_text)).setText(item.getDescription());
        ((TextView) view.findViewById(R.id.repository_star_text)).setText(item.getStargazersCount());
        ((TextView) view.findViewById(R.id.repository_pr_text)).setText(item.getForks());
        ((TextView) view.findViewById(R.id.repository_owner_text)).setText(item.getOwner().getLogin());
        ((TextView) view.findViewById(R.id.repository_owner_nick_text)).setText(item.getOwner().getType());

        view.findViewById(R.id.li_container).setOnClickListener( getActionListener(item));

        return view;
    }


    private View.OnClickListener getActionListener(final Repository item){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(ctx, PullRequestListActivity_.class);
                intent.putExtra(PullRequestListActivity.REPOSITORY_EXTRA, item);
                ctx.startActivity(intent);
            }
        };
    }


}
