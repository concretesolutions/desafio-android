package com.example.rafaelanastacioalves.moby.pulllisting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rafaelanastacioalves.moby.R;
import com.example.rafaelanastacioalves.moby.vo.Pull;
import com.example.rafaelanastacioalves.moby.listeners.RecyclerViewClickListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

class PullViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.pull_text_view_description)
    TextView pullTextViewDescription;

    @BindView(R.id.pull_textview_title)
    TextView pullTextViewTitle;

    @BindView(R.id.pull_textview_owner_username)
    TextView pullTexViewUserName;

    private final RecyclerViewClickListener aRecyclerViewListener;

    @BindView(R.id.pull_linear_layout_container)
    LinearLayout pullLinearLayoutContainer;

    @BindView(R.id.pull_textview_owner_photo)
    ImageView circularImageView;

    public PullViewHolder(View itemView, RecyclerViewClickListener clickListener) {
        super(itemView);
        this.aRecyclerViewListener = clickListener;

        ButterKnife.bind(this, itemView);

        pullLinearLayoutContainer.setOnClickListener(this);
    }

    public void bind(Pull aPull, Context context) {
        pullTextViewDescription.setText(aPull.getBody());
        pullLinearLayoutContainer.setContentDescription("Pull Request number " + (getAdapterPosition() + 1));

        pullTextViewTitle.setText(aPull.getTitle());
        pullTexViewUserName.setText(aPull.getPullUser().getLogin());
        pullTexViewUserName.setHint(aPull.getPullUser().getLogin() + getAdapterPosition());


        Picasso.get()
                .load(aPull.getPullUser().getAvatarUrl())
                .resize(150, 150)
                .centerInside()
                .placeholder(R.drawable.placeholder_user)
                .into(circularImageView);

        pullLinearLayoutContainer.setTag(aPull);

    }


    @Override
    public void onClick(View v) {
        aRecyclerViewListener.onClick(v, getAdapterPosition());

    }
}