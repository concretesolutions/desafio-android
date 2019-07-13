package com.concrete.android.challenge.utils;

import android.content.Context;
import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.concrete.android.challenge.ui.base.BindableAdapter;
import java.util.List;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public class BindingUtils {

  public BindingUtils() {
  }

  @BindingAdapter({ "adapter" })
  public static <T> void addRepositoryItems(RecyclerView recyclerView, List<T> items) {
    BindableAdapter adapter = (BindableAdapter) recyclerView.getAdapter();
    if (adapter != null) {
      adapter.setItems(items);
    }
  }

  @BindingAdapter("imageUrl")
  public static void setImageUrl(ImageView imageView, String url) {
    Context context = imageView.getContext();
    GlideApp.with(context)
        .load(url)
        .apply(RequestOptions.circleCropTransform())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(imageView);
  }
}
