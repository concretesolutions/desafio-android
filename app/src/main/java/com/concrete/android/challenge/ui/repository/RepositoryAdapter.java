package com.concrete.android.challenge.ui.repository;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.concrete.android.challenge.data.model.Repository;
import com.concrete.android.challenge.databinding.ItemRepositoryBinding;
import com.concrete.android.challenge.ui.base.BaseViewHolder;
import com.concrete.android.challenge.ui.base.BindableAdapter;
import java.util.List;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public class RepositoryAdapter extends RecyclerView.Adapter<BaseViewHolder> implements
    BindableAdapter<Repository> {

  private List<Repository> repositoryList;
  private RepositoryItemViewModel.RepositoryItemViewModelListener listener;

  public RepositoryAdapter() {
  }

  public RepositoryAdapter(List<Repository> repositoryList) {
    this.repositoryList = repositoryList;
  }

  @NonNull @Override
  public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemRepositoryBinding itemRepositoryBinding =
        ItemRepositoryBinding.inflate(LayoutInflater.from(parent.getContext()),
            parent, false);
    return new RepositoryViewHolder(itemRepositoryBinding);
  }

  @Override public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
    holder.onBind(position);
  }

  @Override public int getItemCount() {
    return repositoryList.size();
  }

  public void add(List<Repository> repositories) {
    repositoryList.addAll(repositories);
    notifyDataSetChanged();
  }

  public void clearItems() {
    repositoryList.clear();
    notifyDataSetChanged();
  }

  @Override public void setItems(List<Repository> repositories) {
    repositoryList.addAll(repositories);
    notifyDataSetChanged();
  }

  public void setListener(
      RepositoryItemViewModel.RepositoryItemViewModelListener listener) {
    this.listener = listener;
  }

  public class RepositoryViewHolder extends BaseViewHolder {

    private ItemRepositoryBinding repositoryBinding;

    private RepositoryItemViewModel itemViewModel;

    public RepositoryViewHolder(ItemRepositoryBinding repositoryBinding) {
      super(repositoryBinding.getRoot());
      this.repositoryBinding = repositoryBinding;
    }

    @Override public void onBind(int position) {
      Repository repository = repositoryList.get(position);
      itemViewModel = new RepositoryItemViewModel(repository, listener);
      repositoryBinding.setViewModel(itemViewModel);
      repositoryBinding.executePendingBindings();
    }
  }
}
