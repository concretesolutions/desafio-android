package br.com.desafio.ui.adapter.bind;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import br.com.desafio.R;
import br.com.desafio.domain.Repository;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

@EViewGroup(R.layout.repository_item)
public class RepositoryItemView extends LinearLayout {
    private Context context;
    @ViewById TextView name;
    @ViewById TextView description;
    @ViewById TextView forks;
    @ViewById TextView stars;
    @ViewById TextView fullName;
    @ViewById TextView userName;
    @ViewById ImageView photo;

    public RepositoryItemView(Context context) {
        super(context);
        this.context = context;
    }

    public void bind(Repository repository) {
        setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        name.setText(repository.getName());
        description.setText(repository.getDescription());
        forks.setText(repository.getForks().toString());
        stars.setText(repository.getStars().toString());
        fullName.setText(repository.getFullName());
        userName.setText(repository.getOwner().getName());

        if(repository.getOwner() != null && repository.getOwner().getPhoto() != null)
            Picasso.with(context).load(repository.getOwner().getPhoto()).transform(new CropCircleTransformation()).placeholder(R.drawable.user).into(photo);
        else
            Picasso.with(context).load(R.drawable.user).transform(new CropCircleTransformation()).into(photo);
    }
}