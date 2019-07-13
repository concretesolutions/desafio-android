package com.concrete.android.challenge.ui.splash;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import com.concrete.android.challenge.BR;
import com.concrete.android.challenge.R;
import com.concrete.android.challenge.databinding.ActivitySplashBinding;
import com.concrete.android.challenge.ui.base.BaseActivity;
import com.concrete.android.challenge.ui.main.MainActivity;

/**
 * @author Thiago Corredo
 * @since 2019-07-12
 */
public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel>
    implements SplashNavigator {

  private ActivitySplashBinding activitySplashBinding;
  private SplashViewModel splashViewModel;

  @Override public SplashViewModel getViewModel() {
    splashViewModel = ViewModelProviders.of(this, factory).get(SplashViewModel.class);
    return splashViewModel;
  }

  @Override public int getBindingVariable() {
    return BR.viewModel;
  }

  @Override public int getLayoutId() {
    return R.layout.activity_splash;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activitySplashBinding = getViewDataBinding();
    splashViewModel.setNavigator(this);

    Typeface type = Typeface.createFromAsset(getAssets(), "fonts/fff_tusj.ttf");
    activitySplashBinding.title.setTypeface(type);
  }

  @Override public void openMainActivity() {
    Intent intent = MainActivity.newIntent(this);
    startActivity(intent);
  }
}
