package br.com.concretizando.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.concretizando.R;
import br.com.concretizando.activity.DetailActivity;
import br.com.concretizando.activity.MainActivity;
import br.com.concretizando.event.ListRepositoryOnClickListener;
import br.com.concretizando.model.Repository;

public class ListRepositoryAdapter extends RecyclerView.Adapter<ListRepositoryAdapter.ListRepositoryViewHolder> {

    private final List<Repository> list;
    private final Activity act;
    private final RecyclerView recyclerList;
    private final View.OnClickListener mOnClickListener;

    public ListRepositoryAdapter(List<Repository> list, Activity act, RecyclerView recyclerList) {

        this.list = list;
        this.act = act;
        this.recyclerList = recyclerList;
        this.mOnClickListener = new ListRepositoryOnClickListener(act, list, recyclerList);
    }

    @Override
    public ListRepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(act);
        View view = inflater.inflate(R.layout.list_repository, parent, false);
        view.setOnClickListener(mOnClickListener);
        return new ListRepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListRepositoryViewHolder holder, int position) {

        final Repository repo = list.get(position);
        Picasso.with(act).load(repo.getOwner().getAvatar_url()).into(holder.ownerAvatar);
        holder.repositoryName.setText(holder.str1.
                replace("{param1}", repo.getName()));
        holder.repositoryDescription.setText(holder.str2.
                replace("{param1}", repo.getDescription()));
        holder.repositoryStars.setText(holder.str3.
                replace("{param1}", String.valueOf(repo.getStargazers_count())));
        holder.repositoryForks.setText(holder.str4.
                replace("{param1}", String.valueOf(repo.getForks())));
        holder.ownerUser.setText(holder.str5.
                replace("{param1}", repo.getOwner().getLogin()));

    }

    @Override
    public int getItemCount() {

        return this.list.size();
    }

    public Repository getItem(int pos) {

        return this.list.get(pos);
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    public class ListRepositoryViewHolder extends RecyclerView.ViewHolder {

        ImageView ownerAvatar;
        TextView repositoryName;
        TextView repositoryDescription;
        TextView repositoryStars;
        TextView repositoryForks;
        TextView ownerUser;
        String str1;
        String str2;
        String str3;
        String str4;
        String str5;

        public ListRepositoryViewHolder(View itemView) {
            super(itemView);
            ownerAvatar = itemView.findViewById(R.id.ownerAvatar);
            repositoryName = itemView.findViewById(R.id.repositoryName);
            repositoryDescription = itemView.findViewById(R.id.repositoryDescription);
            repositoryStars = itemView.findViewById(R.id.repositoryStars);
            repositoryForks = itemView.findViewById(R.id.repositoryForks);
            ownerUser = itemView.findViewById(R.id.ownerUser);
            str1 = itemView.getResources().getString(R.string.str01);
            str2 = itemView.getResources().getString(R.string.str02);
            str3 = itemView.getResources().getString(R.string.str03);
            str4 = itemView.getResources().getString(R.string.str04);
            str5 = itemView.getResources().getString(R.string.str05);
        }
    }
}


















