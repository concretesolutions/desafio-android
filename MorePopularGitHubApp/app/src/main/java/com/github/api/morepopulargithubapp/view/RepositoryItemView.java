package com.github.api.morepopulargithubapp.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.api.morepopulargithubapp.R;
import com.github.api.morepopulargithubapp.model.vo.Repository;
import com.github.api.morepopulargithubapp.model.vo.User;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import de.hdodenhof.circleimageview.CircleImageView;


@EViewGroup(R.layout.view_repository_item)
public class RepositoryItemView extends FrameLayout {

    @ViewById
    protected CardView cardViewContainer;

    @ViewById
    protected TextView nameRepositoryTxt;

    @ViewById
    protected CircleImageView photoAuthorRepository;

    @ViewById
    protected TextView authorRepository;

    @ViewById
    protected TextView descriptionRepositoryTxt;

    @ViewById
    protected TextView forksCount;

    @ViewById
    protected TextView stargazersCount;


    public RepositoryItemView(Context context) {
        super(context);
    }

    public RepositoryItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RepositoryItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @AfterViews
    void init() {

    }

    public void bind(Repository repository) {
        if (repository != null) {
            cardViewContainer.setTag(repository);

            User user = repository.getOwner();
            nameRepositoryTxt.setText(repository.getName());
            descriptionRepositoryTxt.setText(repository.getDescription());
            if (repository.getForks() != null) {
                forksCount.setText(String.valueOf(repository.getForks()));
            }

            if (repository.getStargazers() != null) {
                stargazersCount.setText(String.valueOf(repository.getStargazers()));
            }

            if (user != null) {
                authorRepository.setText(user.getLogin());
                // Obtem a imagem do objeto user e a insere no componente de imagem instanciado
                Picasso.with(photoAuthorRepository.getContext()).load(user.getPhoto()).into(photoAuthorRepository);
            }
        }
    }

    @Click(R.id.cardViewContainer)
    void initPullRequest() {
            PullRequestActivity_.intent(getContext())
                    .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .repository( (Repository) cardViewContainer.getTag())
                    .start();
    }

}
