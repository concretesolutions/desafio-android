package br.com.guilherme.concrete.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.guilherme.concrete.R;
import br.com.guilherme.concrete.model.PullRequest;

public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.ViewHolder>{
    private List<PullRequest> pullRequests;
    private Context context;

    public PullRequestAdapter(List<PullRequest> pullRequests, Context context){
        this.pullRequests = pullRequests;
        this.context = context;
    }

    @NonNull
    @Override
    public PullRequestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pull_request, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PullRequestAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return pullRequests.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout containerInfos;
        TextView nomePullRequest;
        TextView descricaoPullRequest;

        TextView username;
        ImageView avatarUser;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            containerInfos = itemView.findViewById(R.id.row);
            nomePullRequest = itemView.findViewById(R.id.nome_pull_request);
            descricaoPullRequest = itemView.findViewById(R.id.descricao_pull_request);

            username = itemView.findViewById(R.id.username);
            avatarUser = itemView.findViewById(R.id.foto_user);
        }
    }
}
