package com.example.desafioconcrete.Interface;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.desafioconcrete.Objects.GithubApiResponse;
import com.example.desafioconcrete.R;

public class AdapterRepositoryList extends BaseAdapter {

    private Context context;
    private GithubApiResponse githubApiResponse;
    private LayoutInflater inflater;

    public AdapterRepositoryList(Context context, GithubApiResponse githubApiResponse) {
        this.context = context;
        this.githubApiResponse = githubApiResponse;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return githubApiResponse.getDetailsRepositoryList().size();
    }

    @Override
    public Object getItem(int i) {
        return githubApiResponse.getDetailsRepositoryList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView;
        if (view == null) {
            rowView = inflater.inflate(R.layout.adapter_listview_repository, null);
        } else {
            rowView = view;
        }

        TextView txtRepositoryName = rowView.findViewById(R.id.txtRepositoryName);
        TextView txtRepositoryDetails = rowView.findViewById(R.id.txtRepositoryDetails);
        TextView txtRepositoryForks = rowView.findViewById(R.id.txtRepositoryForks);
        TextView txtRepositoryStars = rowView.findViewById(R.id.txtRepositoryStars);
        TextView txtRepositoryUsername = rowView.findViewById(R.id.txtRepositoryUsername);
        TextView txtRepositoryFullName = rowView.findViewById(R.id.txtRepositoryFullName);

        ImageView imgRepositoryAvatar = rowView.findViewById(R.id.imgRepositoryAvatar);

        txtRepositoryName.setText(githubApiResponse.getDetailsRepositoryList().get(i).getName());
        txtRepositoryDetails.setText(githubApiResponse.getDetailsRepositoryList().get(i).getDescription());
        txtRepositoryForks.setText(String.valueOf(githubApiResponse.getDetailsRepositoryList().get(i).getForks_count()));
        txtRepositoryStars.setText(String.valueOf(githubApiResponse.getDetailsRepositoryList().get(i).getStargazers_count()));
        txtRepositoryUsername.setText(githubApiResponse.getDetailsRepositoryList().get(i).getOwner().getLogin());
        txtRepositoryFullName.setText(githubApiResponse.getDetailsRepositoryList().get(i).getFull_name());

        Glide.with(context).load(githubApiResponse.getDetailsRepositoryList().get(i).getOwner().getAvatar_url()).into(imgRepositoryAvatar);


        return rowView;
    }
}
