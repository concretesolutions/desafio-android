package com.brunorfreitas.desafioconcrete.ui.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.brunorfreitas.desafioconcrete.R;
import com.brunorfreitas.desafioconcrete.data.Model.Item;
import com.brunorfreitas.desafioconcrete.data.Model.PullRequestRepositoryResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterPullRequestRepo extends RecyclerView.Adapter<AdapterPullRequestRepo.MyViewHolder> {

    private Context context;
    private List<PullRequestRepositoryResponse> listPullRequestRepo;
    private String owner;
    private String repo;

    public AdapterPullRequestRepo(Context context, List<PullRequestRepositoryResponse> listPullRequestRepo, String owner, String repo) {
        this.context = context;
        this.listPullRequestRepo = listPullRequestRepo;
        this.owner = owner;
        this.repo = repo;
    }

    public interface I_AdapterPullRequestRepo{
        void onClickPullRequestRepo(int position);
    }

    private I_AdapterPullRequestRepo i_adapterPullRequestRepo;

    public void setI_adapterPullRequestRepo(I_AdapterPullRequestRepo i_adapterPullRequestRepo) {
        this.i_adapterPullRequestRepo = i_adapterPullRequestRepo;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_pull_request_list, viewGroup, false);
        return new MyViewHolder(v, context);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        PullRequestRepositoryResponse response = listPullRequestRepo.get(i);
        myViewHolder.bind(response, i);
    }

    @Override
    public int getItemCount() {
        return listPullRequestRepo.size();
    }

    public void addItens(List<PullRequestRepositoryResponse> listPullRequestRepo) {
        this.listPullRequestRepo = listPullRequestRepo;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        Context context;
        CardView cardView;
        TextView tv_tituloPullRequest;
        TextView tv_bodyPullRequest;
        TextView tv_username;
        TextView tv_NomeSobrenome;
        ImageView iv_user;

        public MyViewHolder(@NonNull View itemView, Context context) {
            super(itemView);

            this.context = context;

            cardView = itemView.findViewById(R.id.item_pull_request_list_cv);
            tv_tituloPullRequest = itemView.findViewById(R.id.item_pull_request_list_tv_titulo_pullrequest);
            tv_bodyPullRequest = itemView.findViewById(R.id.item_pull_request_list_body_pullrequest);
            tv_username = itemView.findViewById(R.id.item_pull_request_list_tv_username);
            tv_NomeSobrenome = itemView.findViewById(R.id.item_pull_request_list_tv_nome_sobrenome);
            iv_user = itemView.findViewById(R.id.item_pull_request_list_iv_user);
        }

        public void bind(PullRequestRepositoryResponse item, final int position){

            String tituloPullRequest = item.getTitle();
            String bodyPullRequest = item.getBody();
            String username = item.getUser().getLogin();
            String imagemUser = item.getUser().getAvatarUrl();

            String data[] = item.getUpdatedAt().split("T");
            String dataFormatada = data[0].replace("-","/");

            tv_tituloPullRequest.setText(tituloPullRequest);
            tv_bodyPullRequest.setText(bodyPullRequest);
            tv_username.setText(username);
            tv_NomeSobrenome.setText(dataFormatada);
            Picasso.get().load(imagemUser).into(iv_user);


            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (i_adapterPullRequestRepo != null) {
                        i_adapterPullRequestRepo.onClickPullRequestRepo(position);
                    }
                }
            });
        }
    }
}
