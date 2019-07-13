package com.concrete.android.challenge.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import com.concrete.android.challenge.ViewModelFactory;
import dagger.android.support.AndroidSupportInjection;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends
    Fragment {

  @Inject
  public ViewModelFactory factory;
  private BaseActivity mActivity;
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

  @Override
  public void onAttach(@NotNull Context context) {
    super.onAttach(context);
    if (context instanceof BaseActivity) {
      this.mActivity = (BaseActivity) context;
    }
  }

  @Override
  public void onDetach() {
    mActivity = null;
    super.onDetach();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    performDependencyInjection();
    super.onCreate(savedInstanceState);
    mViewModel = getViewModel();
    setHasOptionsMenu(true);
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
    return mViewDataBinding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
    mViewDataBinding.setLifecycleOwner(this);
    mViewDataBinding.executePendingBindings();
  }

  private void performDependencyInjection() {
    AndroidSupportInjection.inject(this);
  }

  public BaseActivity getBaseActivity() {
    return mActivity;
  }

  public T getViewDataBinding() {
    return mViewDataBinding;
  }

  public void hideKeyboard() {
    if (mActivity != null) {
      mActivity.hideKeyboard();
    }
  }
}
