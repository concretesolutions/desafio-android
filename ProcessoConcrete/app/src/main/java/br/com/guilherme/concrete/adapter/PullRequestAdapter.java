package br.com.guilherme.concrete.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.com.guilherme.concrete.R;
import br.com.guilherme.concrete.model.PullRequest;
import br.com.guilherme.concrete.presenter.PullRequestPresenter;

public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.ViewHolder>{
    private List<PullRequest> pullRequests;
    private Context context;
    private PullRequestPresenter presenter;

    public PullRequestAdapter(List<PullRequest> pullRequests, Context context, PullRequestPresenter presenter){
        this.pullRequests = pullRequests;
        this.context = context;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public PullRequestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pull_request, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PullRequestAdapter.ViewHolder holder, final int position) {
        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.setCallWeb(pullRequests.get(position).getPathPR(), context);
            }
        });

        holder.nomePullRequest.setText(pullRequests.get(position).getTituloPR());
        holder.descricaoPullRequest.setText(pullRequests.get(position).getBodyPR());
        holder.dataPullRequest.setText(formatarData(pullRequests.get(position).getDataPR()));

        holder.username.setText(pullRequests.get(position).getUser().getNomeUsuario());
        Glide.with(context).load(pullRequests.get(position).getUser().getFotoUsuario()).into(holder.avatarUser);
    }

    @Override
    public int getItemCount() {
        return pullRequests.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout containerInfos;
        ConstraintLayout row;
        TextView nomePullRequest;
        TextView descricaoPullRequest;
        TextView dataPullRequest;

        TextView username;
        ImageView avatarUser;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            row = itemView.findViewById(R.id.row);
            containerInfos = itemView.findViewById(R.id.row);
            nomePullRequest = itemView.findViewById(R.id.nome_pull_request);
            descricaoPullRequest = itemView.findViewById(R.id.descricao_pull_request);
            dataPullRequest = itemView.findViewById(R.id.data_pull_request);

            username = itemView.findViewById(R.id.username);
            avatarUser = itemView.findViewById(R.id.foto_user);
        }
    }

    private String formatarData(String dataFromJson){
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        Date date = null;
        try {
            date = inputFormat.parse(dataFromJson);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("error_parsing_date", String.valueOf(e));
        }

        return outputFormat.format(date);
    }
}
