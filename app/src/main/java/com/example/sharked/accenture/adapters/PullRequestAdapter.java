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
import com.example.sharked.accenture.models.PullRequest;
import com.example.sharked.accenture.models.Repository;
import com.example.sharked.accenture.views.activities.PullRequestListActivity;
import com.example.sharked.accenture.views.activities.PullRequestListActivity_;


public class PullRequestAdapter extends ArrayAdapter<PullRequest> {
    private Context ctx;
    public PullRequestAdapter(@NonNull Context context, @NonNull PullRequest[] objects) {
        super(context, R.layout.li_pull_request, objects);
        ctx = context;

    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = (convertView != null)? convertView :inflater.inflate(R.layout.li_pull_request, parent, false);

        final PullRequest item = getItem(position);

        ((TextView) view.findViewById(R.id.pr_name_text)).setText(item.getTitle());
        ((TextView) view.findViewById(R.id.pr_description_text)).setText(item.getBody());
        ((TextView) view.findViewById(R.id.pr_owner_nick_text)).setText(item.getUser().getLogin());
        ((TextView) view.findViewById(R.id.pr_owner_text)).setText(item.getUser().getType());


        return view;
    }


}
