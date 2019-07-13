package com.concrete.android.challenge.ui.base;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

  public BaseViewHolder(View itemView) {
    super(itemView);
  }

  public abstract void onBind(int position);
}
