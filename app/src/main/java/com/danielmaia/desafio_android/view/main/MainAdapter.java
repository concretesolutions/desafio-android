package com.danielmaia.desafio_android.view.main;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.danielmaia.desafio_android.AppRepo;
import com.danielmaia.desafio_android.R;
import com.danielmaia.desafio_android.model.Owner;
import com.danielmaia.desafio_android.model.Repo;
import com.danielmaia.desafio_android.util.Fonts;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private MainAdapter.MainAdapterListener listener;
    private List<Repo> repoList;

    public interface MainAdapterListener {
        void onItemClick(long ownerId, String repo);
    }

    public MainAdapter(MainAdapter.MainAdapterListener l, List<Repo> r) {
        listener = l;
        this.repoList = r;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_main, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {

        holder.txtTitle.setTypeface(Typeface.createFromAsset(AppRepo.getInstance().getAssets(), Fonts.RALEWAY_MEDIUM));
        holder.txtDesc.setTypeface(Typeface.createFromAsset(AppRepo.getInstance().getAssets(), Fonts.RALEWAY_REGULAR));
        holder.txtForkCount.setTypeface(Typeface.createFromAsset(AppRepo.getInstance().getAssets(), Fonts.RALEWAY_REGULAR));
        holder.txtStar.setTypeface(Typeface.createFromAsset(AppRepo.getInstance().getAssets(), Fonts.RALEWAY_REGULAR));
        holder.txtUsername.setTypeface(Typeface.createFromAsset(AppRepo.getInstance().getAssets(), Fonts.RALEWAY_REGULAR));
        holder.txtName.setTypeface(Typeface.createFromAsset(AppRepo.getInstance().getAssets(), Fonts.RALEWAY_REGULAR));

        final Repo repo = repoList.get(position);

        holder.txtTitle.setText(repo.getName());
        holder.txtDesc.setText(repo.getDescription());
        holder.txtForkCount.setText(String.valueOf(repo.getForks()));
        holder.txtStar.setText(String.valueOf(repo.getStargazers_count()));

        Owner owner = Owner.findByGuid(repo.getOwner_id());

        if (owner != null) {
            holder.txtUsername.setText(owner.getLogin());
            Glide.with(AppRepo.getInstance()).
                    load(owner.getAvatar_url())
                    .placeholder(AppRepo.getInstance().getResources().getDrawable(R.drawable.ic_profile))
                    .into(holder.imgProfile);
        }

        int iconColor = ContextCompat.getColor(AppRepo.getInstance(), R.color.colorAccent);
        holder.imgFork.setBackground(new IconicsDrawable(AppRepo.getInstance()).icon(FontAwesome.Icon.faw_code_fork).sizeDp(10).color(iconColor));
        holder.imgStar.setBackground(new IconicsDrawable(AppRepo.getInstance()).icon(FontAwesome.Icon.faw_star).sizeDp(10).color(iconColor));

        holder.llContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(repo.getOwner_id(), repo.getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    public void setList(List<Repo> list) {
        repoList = list;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout llContainer;
        public TextView txtTitle, txtDesc, txtForkCount, txtStar, txtUsername, txtName;
        public ImageView imgFork, imgStar;
        public CircleImageView imgProfile;
        public MainAdapter.MainAdapterListener listener;

        public ViewHolder(View itemView, final MainAdapter.MainAdapterListener listener) {
            super(itemView);
            this.listener = listener;

            llContainer = itemView.findViewById(R.id.llContainer);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDesc = itemView.findViewById(R.id.txtDesc);
            imgFork = itemView.findViewById(R.id.imgFork);
            txtForkCount = itemView.findViewById(R.id.txtForkCount);
            imgStar = itemView.findViewById(R.id.imgStar);
            txtStar = itemView.findViewById(R.id.txtStar);
            imgProfile = itemView.findViewById(R.id.imgProfile);
            txtUsername = itemView.findViewById(R.id.txtUsername);
            txtName = itemView.findViewById(R.id.txtName);
        }
    }
}
