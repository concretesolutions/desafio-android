package adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.feliperecabarren.desafioandroidconcrete.R;
import com.example.feliperecabarren.desafioandroidconcrete.RepositoryDetailActivity;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import dto.RepositoryDetail;
import dto.RepositoryList;
import utils.UtilsRepository;

/**
 * Created by felipe.recabarren on 20-10-18.
 */

public class RepositoryDetailAdapter extends RecyclerView.Adapter<RepositoryDetailAdapter.ViewHolder>{
    private List<RepositoryDetail> repositoryListsDetail;
    private Activity activity;
    View view;
    private UtilsRepository utilsRepository = new UtilsRepository();

    public RepositoryDetailAdapter(List<RepositoryDetail> repositoryListsDetail,Activity activity){
        this.repositoryListsDetail = repositoryListsDetail;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_repository_detail,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.pullRequestTitle.setText(repositoryListsDetail.get(position).getTitle());
        holder.pullRequestBody.setText(repositoryListsDetail.get(position).getBody());
        holder.pullRequestUserName.setText(repositoryListsDetail.get(position).getUserName());
        Picasso.get().load(repositoryListsDetail.get(position).getAvatar()).into(holder.pullRequestAvatar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //abrir navegador
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(repositoryListsDetail.get(position).getUrlPullRequest()));
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return repositoryListsDetail.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView pullRequestTitle;
        public TextView pullRequestBody;
        public TextView pullRequestUserName;
        public TextView pullRequestNickName;
        public ImageView pullRequestAvatar;
        public View itemView;

        public ViewHolder(View view){
            super(view);
            itemView = view;
            pullRequestTitle = (TextView) view.findViewById(R.id.textViewPullRequestTitle);
            pullRequestBody = (TextView) view.findViewById(R.id.textViewPullRequestBody);
            pullRequestUserName = (TextView) view.findViewById(R.id.textViewPullRequestUserName);
            pullRequestAvatar = (ImageView) view.findViewById(R.id.imageViewPullRequestAvatar);
        }
    }
}
