package br.com.desafio.ui.adapter.bind;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import br.com.desafio.R;
import br.com.desafio.domain.PullRequest;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

@EViewGroup(R.layout.pull_request_item)
public class PullRequestItemView extends LinearLayout {
    private Context context;
    @ViewById TextView title;
    @ViewById TextView description;
    @ViewById TextView fullName;
    @ViewById TextView userName;
    @ViewById ImageView photo;

    public PullRequestItemView(Context context) {
        super(context);
        this.context = context;
    }

    public void bind(PullRequest pullRequest) {
        setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        title.setText(pullRequest.getTitle());
        description.setText(pullRequest.getBody());

        if(pullRequest != null && pullRequest.getHead() != null && pullRequest.getHead().getRepo() != null)
            fullName.setText(pullRequest.getHead().getRepo().getFullName());

        userName.setText(pullRequest.getUser().getName());

        if(pullRequest.getUser() != null && pullRequest.getUser().getPhoto() != null)
            Picasso.with(context).load(pullRequest.getUser().getPhoto()).transform(new CropCircleTransformation()).placeholder(R.drawable.user).into(photo);
        else
            Picasso.with(context).load(R.drawable.user).transform(new CropCircleTransformation()).into(photo);
    }
}