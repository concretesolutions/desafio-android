package adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.feliperecabarren.desafioandroidconcrete.R;
import com.example.feliperecabarren.desafioandroidconcrete.RepositoryDetailActivity;
import com.example.feliperecabarren.desafioandroidconcrete.RepositoryListActivity;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.File;
import java.util.List;

import dto.RepositoryDetail;
import dto.RepositoryList;
import utils.UtilsRepository;

/**
 * Created by felipe.recabarren on 20-10-18.
 */

public class RepositoryListAdapter extends RecyclerView.Adapter<RepositoryListAdapter.ViewHolder>{

    private List<RepositoryList> repositoryLists;
    private Context context;
    private Activity activity;
    View view;
    private UtilsRepository utilsRepository = new UtilsRepository();
    ProgressDialog progressDialog;

    public RepositoryListAdapter(List<RepositoryList> repositoryLists,Context context,Activity activity){
        this.repositoryLists = repositoryLists;
        this.context = context;
        this.activity = activity;
        this.progressDialog = new ProgressDialog(activity);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_repository_list,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RepositoryListAdapter.ViewHolder holder, final int position) {

        holder.repositoryName.setText(repositoryLists.get(position).getRepositoryName());
        holder.repositoryDescription.setText(repositoryLists.get(position).getRepositoryDescription());
        holder.repositoryBranch.setText("Branch:" +repositoryLists.get(position).getBranch());
        holder.repositoryFavorite.setText("Forks: "+repositoryLists.get(position).getFavorite());
        holder.repositoryUserName.setText("UserName: "+repositoryLists.get(position).getUserName());
        //holder.repositoryAvatar.setImageBitmap(utilsRepository.loadBitmap(repositoryLists.get(position).getAvatar()));
        Picasso.get().load(repositoryLists.get(position).getAvatar()).into(holder.repositoryAvatar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Cargando...");
                progressDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(context, RepositoryDetailActivity.class);
                        intent.putExtra("creator",repositoryLists.get(position).getUserName());
                        intent.putExtra("repository",repositoryLists.get(position).getRepositoryName());
                        intent.putExtra("opened",repositoryLists.get(position).getOpened());
                        intent.putExtra("closed",repositoryLists.get(position).getClosed());
                        activity.startActivity(intent);
                        progressDialog.dismiss();
                    }
                }, 1000);

            }
        });
    }

    @Override
    public int getItemCount() {
        return repositoryLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView repositoryName;
        public TextView repositoryDescription;
        public TextView repositoryBranch;
        public TextView repositoryFavorite;
        public TextView repositoryUserName;
        public ImageView repositoryAvatar;
        public View itemView;

        public ViewHolder(View view){
            super(view);
            itemView = view;
            repositoryName = (TextView) view.findViewById(R.id.textViewRepositoryName);
            repositoryDescription = (TextView) view.findViewById(R.id.textViewRepositoryDescription);
            repositoryBranch = (TextView) view.findViewById(R.id.textViewRepositoryBranch);
            repositoryFavorite = (TextView) view.findViewById(R.id.textViewRepositoryFavorite);
            repositoryUserName = (TextView) view.findViewById(R.id.textViewRepositoryUsername);
            repositoryAvatar = (ImageView) view.findViewById(R.id.imageViewRepositoryAvatar);
        }
    }
}
