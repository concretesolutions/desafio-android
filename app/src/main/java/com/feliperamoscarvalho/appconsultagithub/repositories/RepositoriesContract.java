package com.feliperamoscarvalho.appconsultagithub.repositories;

import android.support.annotation.NonNull;

import com.feliperamoscarvalho.appconsultagithub.data.model.Item;

import java.util.List;

/**
 * Contract that defines how the repositories screen should work.
 * View actions should be implemented by the view and user actions
 * should be implemented by the presenter.
 */
public interface RepositoriesContract {

    interface View {

        void setLoading(boolean isActive);

        void showRepositoryItems(List<Item> repositories);

        void showDetailsUI(String login, String repositoryName);
    }

    interface UserActionsListener {

        void loadRepository();

        void openDetails(@NonNull Item item);
    }
}
