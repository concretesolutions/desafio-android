package br.com.desafioandroid.holder;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PullsHolder {
    TextView repositoryName, data, repositoryDescription, username, fullName;
    ImageView imgUser;
    ProgressBar progressBar;

    public TextView getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(TextView repositoryName) {
        this.repositoryName = repositoryName;
    }

    public TextView getData() {
        return data;
    }

    public void setData(TextView data) {
        this.data = data;
    }

    public TextView getRepositoryDescription() {
        return repositoryDescription;
    }

    public void setRepositoryDescription(TextView repositoryDescription) {
        this.repositoryDescription = repositoryDescription;
    }

    public TextView getUsername() {
        return username;
    }

    public void setUsername(TextView username) {
        this.username = username;
    }

    public TextView getFullName() {
        return fullName;
    }

    public void setFullName(TextView fullName) {
        this.fullName = fullName;
    }

    public ImageView getImgUser() {
        return imgUser;
    }

    public void setImgUser(ImageView imgUser) {
        this.imgUser = imgUser;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }
}
