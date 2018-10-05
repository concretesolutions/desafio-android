package com.github.api.morepopulargithubapp.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.api.morepopulargithubapp.R;
import com.github.api.morepopulargithubapp.model.vo.PullRequest;
import com.github.api.morepopulargithubapp.model.vo.User;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import de.hdodenhof.circleimageview.CircleImageView;

@EViewGroup(R.layout.view_pull_request_item)
public class PullRequestItemView extends FrameLayout {

    protected CardView cardViewPullRequestContainer;

    @ViewById
    protected TextView titlePullRequest;

    @ViewById
    protected TextView bodyPullRequest;

    @ViewById
    protected CircleImageView photoAuthorPullRequest;

    @ViewById
    protected TextView loginPullRequest;

    public PullRequestItemView(@NonNull Context context) {
        super(context);
    }

    public PullRequestItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PullRequestItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @AfterViews
    void init() {

    }

    public void bind(PullRequest pullRequest) {
        if (pullRequest != null) {

            User user = pullRequest.getUser();
            String urlWebSite = pullRequest.getUrlWebSite();

            titlePullRequest.setText(pullRequest.getTitle());
            bodyPullRequest.setText(pullRequest.getBody());
            if (!TextUtils.isEmpty(urlWebSite)) {
                titlePullRequest.setTag(urlWebSite);
            }

            if (user != null) {
                loginPullRequest.setText(user.getLogin());
                // Obtem a imagem do objeto user e a insere no componente de imagem instanciado
                Picasso.with(photoAuthorPullRequest.getContext()).load(user.getPhoto()).into(photoAuthorPullRequest);
            }
        }
    }

    @Click(R.id.cardViewPullRequestContainer)
    void showPullRequest() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse((String) titlePullRequest.getTag()));
        getContext().startActivity(browserIntent);
    }

}
