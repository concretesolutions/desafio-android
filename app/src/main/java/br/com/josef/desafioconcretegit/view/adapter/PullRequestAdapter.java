package br.com.josef.desafioconcretegit.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.josef.desafioconcretegit.R;
import br.com.josef.desafioconcretegit.model.pojo.pull.PullRequest;
import br.com.josef.desafioconcretegit.view.interfaces.PullRequestOnclick;

public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.ViewHolder> {

    private List<PullRequest> pullRequestList;
    private PullRequestOnclick listener;

    public PullRequestAdapter(List<PullRequest> pullRequestList, PullRequestOnclick listener) {
        this.pullRequestList = pullRequestList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public PullRequestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_recycler_pull_request, parent, false);

        return new PullRequestAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PullRequestAdapter.ViewHolder holder, int position) {
        final PullRequest pullRequest = pullRequestList.get(position);
        holder.onBind(pullRequest);

        holder.itemView.setOnClickListener(v -> listener.OnClick(pullRequest));
    }

    @Override
    public int getItemCount() {
        return pullRequestList.size();
    }

    public void atualizaLista(List<PullRequest> novaLista) {
        if (this.pullRequestList.isEmpty()) {
            this.pullRequestList = novaLista;
        } else {
            this.pullRequestList.addAll(novaLista);
        }
        notifyDataSetChanged();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView userIcon;
        public TextView titulo;
        public TextView desc;
        public TextView username;
        public TextView nomeSobrenome;
        public TextView data;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.txtTituloPull);
            desc = itemView.findViewById(R.id.txtDescPull);
            username = itemView.findViewById(R.id.txtUserPull);
            nomeSobrenome = itemView.findViewById(R.id.txtNomeSobrenome);
            userIcon = itemView.findViewById(R.id.fotoPerfilCard);
            data = itemView.findViewById(R.id.txtData);


        }

        public void onBind(PullRequest pullRequest) {

            if (pullRequest == null) {
                titulo.setText("Não há Pull Requests ¯|_(ツ)_/¯");
            } else {

                try {
                    titulo.setText(pullRequest.getTitle());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Titulo", "error " + e);
                }

                try {
                    desc.setText(pullRequest.getBody());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Descrição", "error" + e);
                }

                try {
                    username.setText(pullRequest.getUser().getLogin());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("UserName", "error " + e);
                }

                try {
                    nomeSobrenome.setText(pullRequest.getHead().getRepo().getFullName());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("FullName", "error " + e);
                    nomeSobrenome.setText("¯|_(ツ)_/¯");
                }



                try {
                    Picasso.get().load(pullRequest.getUser().getAvatarUrl()).into(userIcon);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Picasso", "error" + e);

                }

                String[] date = pullRequest.getCreatedAt().split("T");
                data.setText(date[0]);

            }
        }


    }


}
