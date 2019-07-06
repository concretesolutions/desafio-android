package br.com.githubjavapop.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.githubjavapop.R;
import br.com.githubjavapop.entidade.PullRequest;
import br.com.githubjavapop.viewHolder.PullRequestViewHolder;

public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestViewHolder> implements
        View.OnClickListener {

    private List<PullRequest> listaPullRequest;
    private View.OnClickListener clickListener;

    public PullRequestAdapter(List<PullRequest> listaPullRequest) {
        this.listaPullRequest = listaPullRequest;
    }

    @NonNull
    @Override
    public PullRequestViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.layout_pullrequest, viewGroup, false);
        view.setOnClickListener(this);

        return new PullRequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PullRequestViewHolder pullRequestViewHolder, int i) {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String pullRequestData;

        if(listaPullRequest != null && listaPullRequest.size() > 0) {
            PullRequest pullRequest = listaPullRequest.get(i);
            pullRequestViewHolder.titulo.setText(pullRequest.getTitulo());
            pullRequestData = "Data de criação: " + dateFormat.format(pullRequest.getData());
            pullRequestViewHolder.data.setText(pullRequestData);
            pullRequestViewHolder.body.setText(pullRequest.getBody());
            pullRequestViewHolder.username.setText(pullRequest.getUsuario().getUsername());
            Picasso.get().load(pullRequest.getUsuario().getFoto()).into(pullRequestViewHolder.foto);
        }

    }

    @Override
    public int getItemCount() {
        return listaPullRequest.size();
    }

    public void setOnClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public void onClick(View v) {
        if(clickListener != null) clickListener.onClick(v);
    }
}
