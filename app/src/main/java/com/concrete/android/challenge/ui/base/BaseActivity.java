package com.concrete.android.challenge.ui.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import com.concrete.android.challenge.R;
import com.concrete.android.challenge.ViewModelFactory;
import dagger.android.AndroidInjection;
import javax.inject.Inject;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends
    AppCompatActivity {

  @Inject
  public ViewModelFactory factory;

  private T mViewDataBinding;
  private V mViewModel;

  /**
   * Override for set view model
   *
   * @return view model instance
   */
  public abstract V getViewModel();

  /**
   * Override for set binding variable
   *
   * @return variable id
   */
  public abstract int getBindingVariable();

  /**
   * @return layout resource id
   */
  public abstract @LayoutRes int getLayoutId();

  public void performDependencyInjection() {
    AndroidInjection.inject(this);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    performDependencyInjection();
    super.onCreate(savedInstanceState);
    performDataBinding();
  }

  private void performDataBinding() {
    mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
    this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
    mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
    mViewDataBinding.setLifecycleOwner(this);
    mViewDataBinding.executePendingBindings();
  }

  public T getViewDataBinding() {
    return mViewDataBinding;
  }

  public void hideKeyboard() {
    View view = this.getCurrentFocus();
    if (view != null) {
      InputMethodManager imm = (InputMethodManager)
          getSystemService(Context.INPUT_METHOD_SERVICE);

      if (imm != null) {
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
      }
    }
  }

  protected void setActionbarNavigationAsBack(Toolbar toolbar) {
    if (toolbar == null) {
      return;
    }

    toolbar.setNavigationIcon(getVectorDrawable(R.drawable.ic_arrow_back_white_24dp));
    toolbar.setNavigationOnClickListener(view -> onBackPressed());
  }

  protected Drawable getVectorDrawable(int drawable) {
    return VectorDrawableCompat.create(getResources(), drawable, getTheme());
  }
}
