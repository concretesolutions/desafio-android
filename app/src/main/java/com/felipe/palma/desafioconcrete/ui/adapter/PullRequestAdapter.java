package com.felipe.palma.desafioconcrete.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.felipe.palma.desafioconcrete.R;
import com.felipe.palma.desafioconcrete.domain.response.PullRequestResponse;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Felipe Palma on 13/07/2019.
 */
public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.ViewHolder> {

    private Context context;
    private List<PullRequestResponse> itemsList;
    private List<PullRequestResponse> itemsListFiltered;
    private RecyclerItemClickListener listener;



    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNamePull, txtDescPull, txtNameUserPull, txtStatusPull, txtDateOpened;
        ImageView imgUserPull;

        ViewHolder(View view) {
            super(view);
            txtNamePull = view.findViewById(R.id.txt_name_pull);
            txtDescPull = view.findViewById(R.id.txt_desc_pull);
            txtNameUserPull = view.findViewById(R.id.txt_name_user_pull);
            txtStatusPull = view.findViewById(R.id.txt_status_pull);
            txtDateOpened = view.findViewById(R.id.txt_date_opened);

            imgUserPull = view.findViewById(R.id.img_user_pull);


            view.setOnClickListener(view1 -> listener.onItemClick(itemsListFiltered.get(getAdapterPosition())));
        }
    }

    public PullRequestAdapter(Context context, List<PullRequestResponse> itemsList, RecyclerItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.itemsList = itemsList;
        this.itemsListFiltered = itemsList;
    }


    @Override
    public PullRequestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_pullrequest, parent, false);

        return new PullRequestAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PullRequestAdapter.ViewHolder holder, final int position) {
        final PullRequestResponse mPullRequest = this.itemsListFiltered.get(position);

        holder.txtNamePull.setText(mPullRequest.getTitle());
        holder.txtDescPull.setText(mPullRequest.getBody());

        holder.txtNameUserPull.setText(mPullRequest.getUser().getLogin());
        holder.txtStatusPull.setText(mPullRequest.getState());

        holder.txtDateOpened.setText(dateFormat(mPullRequest.getCreatedAt()));





        Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(20)
                .oval(true)
                .build();

        Picasso.with(context)
                .load(mPullRequest.getUser().getAvatarUrl())
                .placeholder(R.drawable.ic_loading)
                .fit()
                .transform(transformation)
                .into(holder.imgUserPull);


    }


    @Override
    public int getItemCount() {
        return itemsListFiltered.size();
    }

    private String dateFormat(String dateStr) {
        String result;
        result = dateStr.substring(0,10);
        Log.d("DATE", result);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = formatter.parse(result);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            result = dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }



        return result;
    }
}
