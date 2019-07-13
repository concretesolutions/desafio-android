package com.felipe.palma.desafioconcrete.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.felipe.palma.desafioconcrete.R;
import com.felipe.palma.desafioconcrete.domain.model.Item;
import com.github.abdularis.civ.AvatarImageView;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Felipe Palma on 12/07/2019.
 */
public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<Item> itemsList;
    private List<Item> itemsListFiltered;
    private RecyclerItemClickListener listener;



    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameRepo, txtDescRepo, txtNameUserRepo, txtSurnameUserRepo, txtForkCount,txtStarCount;
        ImageView imgUserRepo;

        ViewHolder(View view) {
            super(view);
            txtNameRepo = view.findViewById(R.id.txt_name_repo);
            txtDescRepo = view.findViewById(R.id.txt_desc_repo);
            txtNameUserRepo = view.findViewById(R.id.txt_name_user_repo);
            txtSurnameUserRepo = view.findViewById(R.id.txt_surname_user_repo);
            txtForkCount = view.findViewById(R.id.txt_fork_repo);
            txtStarCount = view.findViewById(R.id.txt_star_repo);

            imgUserRepo = view.findViewById(R.id.img_user_repo);


            view.setOnClickListener(view1 -> listener.onItemClick(itemsListFiltered.get(getAdapterPosition())));
        }
    }

    public RepositoryAdapter(Context context, List<Item> itemsList, RecyclerItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.itemsList = itemsList;
        this.itemsListFiltered = itemsList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_repository, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Item mRepository = this.itemsListFiltered.get(position);

        holder.txtNameRepo.setText(mRepository.getName());
        holder.txtDescRepo.setText(mRepository.getDescription());

        holder.txtNameUserRepo.setText(mRepository.getOwner().getLogin());
        holder.txtSurnameUserRepo.setText(mRepository.getFullName());

        holder.txtForkCount.setText(String.valueOf(mRepository.getForksCount()));
        holder.txtStarCount.setText(String.valueOf(mRepository.getStargazersCount()));





        Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(20)
                .oval(true)
                .build();

        Picasso.with(context)
                .load(mRepository.getOwner().getAvatarUrl())
                .placeholder(R.drawable.ic_loading)
                .fit()
                .transform(transformation)
                .into(holder.imgUserRepo);


    }

    public void addRepoItems(List<Item> items) {
        itemsListFiltered.addAll(items);
        //notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    itemsListFiltered = itemsList;
                } else {
                    List<Item> filteredList = new ArrayList<>();
                    for (Item repo : itemsList) {
                        if (repo.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(repo);
                        }
                    }
                    itemsListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = itemsListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                itemsListFiltered = (ArrayList<Item>) filterResults.values;

                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        return itemsListFiltered.size();
    }

}
