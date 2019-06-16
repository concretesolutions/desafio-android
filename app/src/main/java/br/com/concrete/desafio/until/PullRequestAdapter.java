package br.com.concrete.desafio.until;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.concrete.desafio.R;
import br.com.concrete.desafio.model.PullRequest;

public class PullRequestAdapter extends GenericAdapter<PullRequest> {

    public PullRequestAdapter(PullRequest[] dataSet) {
        super(dataSet);
    }

    @NonNull
    @Override
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.rv_element_pull_request, viewGroup, false);

        return new GenericViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GenericViewHolder viewHolder, int i) {
        final ConstraintLayout constraintLayout = viewHolder.constraintLayout;
        final Context context = constraintLayout.getContext();

        final TextView tvOwnerDate = constraintLayout.findViewById(R.id.tvOwnerDatePR);
        final TextView tvName = constraintLayout.findViewById(R.id.tvNamePR);
        final TextView tvDescription = constraintLayout.findViewById(R.id.tvDescriptionPR);
        final ImageView ivPhoto = constraintLayout.findViewById(R.id.ivPhotoPR);

        if (dataSet.size() == 0) return;

        tvName.setText(dataSet.get(i).getTitle());

        String description = dataSet.get(i).getBody();
        if (description.length() > 0) tvDescription.setText(description);
        else tvDescription.setText(context.getString(R.string.no_description));

        String simpleDate = formatDate(dataSet.get(i).getCreated_at());
        tvOwnerDate.setText(context.getString(R.string.user_open_pull_date, dataSet.get(i).getUser().getLogin(), simpleDate));

        String photoUrl = dataSet.get(i).getUser().getAvatar_url() + "&s=50";
        Picasso.get().load(photoUrl).into(ivPhoto);

        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pullRequestUrl = dataSet.get(viewHolder.getAdapterPosition()).getHtml_url();

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pullRequestUrl));
                context.startActivity(browserIntent);
            }
        });
    }

    @SuppressLint("SimpleDateFormat")
    private String formatDate(String date) {
        String dateString = "";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            Date convertedDate = format.parse(date);
            SimpleDateFormat simpleDate =  new SimpleDateFormat("dd/MM/yyyy");
            dateString = simpleDate.format(convertedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateString;
    }
}
