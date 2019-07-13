package com.concrete.android.challenge.ui.repository;

import androidx.databinding.ObservableField;
import com.concrete.android.challenge.data.model.Repository;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public class RepositoryItemViewModel {

  public ObservableField<String> title;
  public ObservableField<String> content;
  public ObservableField<String> imageUrl;
  public ObservableField<String> userName;
  public ObservableField<String> forkNumber;
  public ObservableField<String> starNumber;
  public ObservableField<String> licenseName;

  private Repository repository;
  private RepositoryItemViewModelListener listener;

  public RepositoryItemViewModel(Repository repository, RepositoryItemViewModelListener listener) {
    this.repository = repository;
    this.listener = listener;
    title = new ObservableField<>(repository.getName());
    content = new ObservableField<>(repository.getDescription());
    imageUrl = new ObservableField<>(repository.getOwner().getAvatarUrl());
    userName = new ObservableField<>(repository.getOwner().getLogin());
    forkNumber = new ObservableField<>(repository.getForks().toString());
    starNumber = new ObservableField<>(repository.getStargazersCount().toString());
    if (repository.getLicense() != null) {
      licenseName = new ObservableField<>(repository.getLicense().getName());
    } else {
      licenseName = new ObservableField<>("Licença não informada");
    }
  }

  public void onItemClick() {
    listener.onItemClick(repository);
  }

  public interface RepositoryItemViewModelListener {
    void onItemClick(Repository repository);
  }
}
