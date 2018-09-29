package br.com.alura.javapop.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

import br.com.alura.javapop.R;
import br.com.alura.javapop.model.PullRequest;

public class ListaPullRequestAdapter extends RecyclerView.Adapter<ListaPullRequestAdapter.PullRequestViewHolder>{

    private final List<PullRequest> pullRequests;
    private final Context context;
    private final OnItemClickListener listener;

    public ListaPullRequestAdapter(Context context, List<PullRequest> pullRequests, OnItemClickListener listener){
        this.context = context;
        this.pullRequests = pullRequests;
        this.listener = listener;
    }

    @Override
    public ListaPullRequestAdapter.PullRequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pull_requests, parent, false);
        return new PullRequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListaPullRequestAdapter.PullRequestViewHolder holder, int position) {
        PullRequest pullRequest = pullRequests.get(position);
        holder.vincula(pullRequest, listener);
    }

    @Override
    public int getItemCount() {
        return pullRequests.size();
    }

    public List<PullRequest> adiciona(List<PullRequest> pullRequest){
        this.pullRequests.addAll(pullRequest);
        notifyDataSetChanged();
        return this.pullRequests;
    }

    class PullRequestViewHolder extends RecyclerView.ViewHolder {

        private final TextView titulo;
        private final TextView corpo;
        private final TextView nomeUsuario;
        private final TextView viewData;

        private final ImageView avatar;

        public PullRequestViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.item_pull_reques_titulo);
            corpo = itemView.findViewById(R.id.item_pull_request_corpo);
            nomeUsuario = itemView.findViewById(R.id.item_pull_reques_nome_usuario);
            viewData = itemView.findViewById(R.id.item_pull_request_data);
            avatar = (ImageView) itemView.findViewById(R.id.item_pull_reques_avatar);
        }

        public void vincula(final PullRequest pullRequest, final OnItemClickListener listener){
            titulo.setText(pullRequest.getTitulo());
            corpo.setText(pullRequest.getCorpo());
            nomeUsuario.setText(pullRequest.getUsuario().getNome());
            Picasso.get().load(pullRequest.getUsuario().getUrlAvatar()).into(avatar);

            Calendar calendar = pullRequest.getData();
            String data = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.YEAR);
            viewData.setText(data);

            itemView.setOnClickListener(clickRepositorio(pullRequest, listener));
        }

        @NonNull
        private View.OnClickListener clickRepositorio(final PullRequest pullRequest, final OnItemClickListener listener) {
            return new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(pullRequest);
                }
            };
        }

    }

    public interface OnItemClickListener{
        void onItemClick(PullRequest pullRequest);
    }
}
