package br.com.desafioandroid.holder;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RepositoryHolder {
    TextView repositoryName, repositoryDescription, countForks, countStars, username, fullName;
    ImageView imgUser;

    public TextView getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(TextView repositoryName) {
        this.repositoryName = repositoryName;
    }

    public TextView getRepositoryDescription() {
        return repositoryDescription;
    }

    public void setRepositoryDescription(TextView repositoryDescription) {
        this.repositoryDescription = repositoryDescription;
    }

    public TextView getCountForks() {
        return countForks;
    }

    public void setCountForks(TextView countForks) {
        this.countForks = countForks;
    }

    public TextView getCountStars() {
        return countStars;
    }

    public void setCountStars(TextView countStars) {
        this.countStars = countStars;
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


}
