package isaacborges.com.projetoconcrete.recyclerview.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import isaacborges.com.projetoconcrete.R;
import isaacborges.com.projetoconcrete.model.PullRequest;
import isaacborges.com.projetoconcrete.recyclerview.adapters.listeners.OnPullRequestItemClickListener;
import isaacborges.com.projetoconcrete.utils.DataUtil;

public class ListaPullRequestAdapter extends RecyclerView.Adapter<ListaPullRequestAdapter.PullRequestViewHolder> {

    private final Context context;
    private final List<PullRequest> pullRequests;
    private OnPullRequestItemClickListener onPullRequestItemClickListener;

    public ListaPullRequestAdapter(Context context){
        this.context = context;
        this.pullRequests = new ArrayList<>();
    }

    public void setOnPullRequestItemClickListener(OnPullRequestItemClickListener onPullRequestItemClickListener){
        this.onPullRequestItemClickListener = onPullRequestItemClickListener;
    }

    @Override
    public PullRequestViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_pull_request, viewGroup, false);
        return new PullRequestViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull PullRequestViewHolder viewHolder, int position) {
        PullRequest pullRequest = pullRequests.get(position);
        viewHolder.vincula(pullRequest);
    }

    @Override
    public int getItemCount() {
        return pullRequests.size();
    }

    public void atualiza(List<PullRequest> listaPullRequests){
        if(listaPullRequests != null) {
            pullRequests.addAll(listaPullRequests);
            atualizaLista();
        }
    }

    public void atualizaLista() {
        notifyDataSetChanged();
    }

    public List<PullRequest> getListaPullRequests() {
        return pullRequests;
    }

    class PullRequestViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtData, txtTitulo, txtDesricao, txtNomeAutor;
        private CircleImageView imgAutor;

        private PullRequest pullRequest;

        public PullRequestViewHolder(View itemView) {
            super(itemView);

            txtTitulo = itemView.findViewById(R.id.item_pull_request_txtTitulo);
            txtDesricao = itemView.findViewById(R.id.item_pull_request_txtDescricao);
            txtNomeAutor = itemView.findViewById(R.id.item_pull_request_txtNomeAutor);
            imgAutor = itemView.findViewById(R.id.item_pull_request_imgAutor);
            txtData = itemView.findViewById(R.id.item_pull_request_txtData);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPullRequestItemClickListener.onItemClick(pullRequest, getAdapterPosition());
                }
            });
        }

        public void vincula(PullRequest pullRequest) {
            this.pullRequest = pullRequest;
            preencheCampos(pullRequest);
        }

        private void preencheCampos(PullRequest pullRequest) {
            txtTitulo.setText(pullRequest.getTitle());
            txtDesricao.setText(pullRequest.getBody());
            txtNomeAutor.setText(pullRequest.getUser().getLogin());
            Picasso.get().load(pullRequest.getUser().getAvatar_url()).into(imgAutor);

            String dataFormatada = DataUtil.formataDataDiaMesAno(pullRequest.getCreated_at());
            txtData.setText(dataFormatada);
        }
    }

}
