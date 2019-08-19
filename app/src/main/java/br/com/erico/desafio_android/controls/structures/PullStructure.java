package br.com.erico.desafio_android.controls.structures;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.erico.desafio_android.R;
import br.com.erico.desafio_android.controls.interfaces.Services;
import br.com.erico.desafio_android.models.PullRequest;

public class PullStructure extends RecyclerView.Adapter<PullStructure.ViewHolder> {

    private List<PullRequest> listaRequests;
    private Context context;
    private PullRequestListener pullRequestListener;
    private Services gitServices;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtPullTitle;
        public TextView txtPullBody;
        public TextView txtData;
        public TextView txtNomeAutor;
        public ImageView imagemAutor;
        PullRequestListener pullListener;

        public ViewHolder(View itemView, PullRequestListener pullRequestListener) {
            super(itemView);

            txtPullTitle = itemView.findViewById(R.id.txtPullTitle);
            txtPullBody = itemView.findViewById(R.id.txtPullBody);
            txtData = itemView.findViewById(R.id.txtDate);
            txtNomeAutor = itemView.findViewById(R.id.txtPullNameAuth);
            imagemAutor = itemView.findViewById(R.id.pullImageAuth);

            this.pullListener = pullRequestListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            PullRequest pullRequest = getPullRequest(getAdapterPosition());
            this.pullListener.onPullRequestClick(pullRequest.getPullRequestUrl());
            notifyDataSetChanged();
        }

    }

    public PullStructure(Context context, List<PullRequest> pullRequests, PullRequestListener pullRequestListener) {
        this.context = context;
        this.listaRequests = pullRequests;
        this.pullRequestListener = pullRequestListener;
    }

    @Override
    public PullStructure.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View pullRequestView = inflater.inflate(R.layout.fragment_description_request, parent, false);

        ViewHolder viewHolder = new ViewHolder(pullRequestView, this.pullRequestListener);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PullRequest pullRequest = listaRequests.get(position);
        TextView txtPullTitle = holder.txtPullTitle;
        TextView txtPullBody = holder.txtPullBody;
        TextView txtDate = holder.txtData;
        TextView txtNameAuth = holder.txtNomeAutor;
        ImageView imageAuth = holder.imagemAutor;

        txtPullTitle.setText(pullRequest.getTitle());
        txtPullBody.setText(pullRequest.getBody());
        txtNameAuth.setText(pullRequest.getUser().getUsername());
        txtDate.setText(formatDate(pullRequest.getDate()));

        Picasso.with(context)
                .load(pullRequest.getUser().getPhoto())
                .resize(160, 160)
                .into(imageAuth);
    }

    @Override
    public int getItemCount() {
        return listaRequests.size();
    }

    public void updatePullRequest(List<PullRequest> pullRequests) {
        this.listaRequests = pullRequests;
        notifyDataSetChanged();
    }

    public PullRequest getPullRequest(int adapterPosition) {
        return listaRequests.get(adapterPosition);
    }

    public interface PullRequestListener {
        void onPullRequestClick(String url);
    }

    public String formatDate(String dateA) {

        String dateList = dateA;
        String dia = dateList.substring(8, 10);
        String mes = dateList.substring(5, 7);
        String ano = dateList.substring(0, 4);

        String date = dia +"/"+ mes +"/"+ ano;

        return date;
    }
}
