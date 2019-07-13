package com.concrete.android.challenge.ui.pull_request;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.concrete.android.challenge.data.model.PullRequest;
import com.concrete.android.challenge.databinding.ItemPullRequestBinding;
import com.concrete.android.challenge.ui.base.BaseViewHolder;
import com.concrete.android.challenge.ui.base.BindableAdapter;
import java.util.List;

/**
 * @author Thiago Corredo
 * @since 2019-07-12
 */
public class PullRequestAdapter extends RecyclerView.Adapter<BaseViewHolder> implements
    BindableAdapter<PullRequest> {

  private List<PullRequest> pullRequests;

  private PullRequestItemViewModel.PullRequestItemViewModelListener listener;

  public PullRequestAdapter() {
  }

  public PullRequestAdapter(List<PullRequest> pullRequests) {
    this.pullRequests = pullRequests;
  }

  @NonNull @Override
  public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemPullRequestBinding itemPullRequestBinding =
        ItemPullRequestBinding.inflate(LayoutInflater.from(parent.getContext()),
            parent, false);
    return new PullRequestViewHolder(itemPullRequestBinding);
  }

  @Override public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
    holder.onBind(position);
  }

  @Override public int getItemCount() {
    return pullRequests.size();
  }

  public void add(List<PullRequest> pullRequests) {
    this.pullRequests.addAll(pullRequests);
    notifyDataSetChanged();
  }

  public void clearItems() {
    pullRequests.clear();
    notifyDataSetChanged();
  }

  @Override public void setItems(List<PullRequest> pullRequests) {
    this.pullRequests.addAll(pullRequests);
    notifyDataSetChanged();
  }

  public void setListener(
      PullRequestItemViewModel.PullRequestItemViewModelListener listener) {
    this.listener = listener;
  }

  public class PullRequestViewHolder extends BaseViewHolder {

    private ItemPullRequestBinding itemPullRequestBinding;

    private PullRequestItemViewModel itemViewModel;

    public PullRequestViewHolder(ItemPullRequestBinding itemPullRequestBinding) {
      super(itemPullRequestBinding.getRoot());
      this.itemPullRequestBinding = itemPullRequestBinding;
    }

    @Override public void onBind(int position) {
      PullRequest pullRequest = pullRequests.get(position);
      itemViewModel = new PullRequestItemViewModel(pullRequest, listener);
      itemPullRequestBinding.setViewModel(itemViewModel);
      itemPullRequestBinding.executePendingBindings();
    }
  }
}
