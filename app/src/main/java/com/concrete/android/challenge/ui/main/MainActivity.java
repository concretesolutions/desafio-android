package com.concrete.android.challenge.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import com.concrete.android.challenge.BR;
import com.concrete.android.challenge.R;
import com.concrete.android.challenge.databinding.ActivityMainBinding;
import com.concrete.android.challenge.databinding.NavHeaderBinding;
import com.concrete.android.challenge.ui.base.BaseActivity;
import com.concrete.android.challenge.ui.repository.RepositoryFragment;
import com.google.android.material.navigation.NavigationView;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel>
    implements MainNavigator {

  private ActivityMainBinding mActivityMainBinding;
  private MainViewModel mMainViewModel;
  private DrawerLayout mDrawer;
  private NavigationView mNavigationView;
  private FragmentManager fragmentManager;

  private int lastMenu;

  public static Intent newIntent(Context context) {
    return new Intent(context, MainActivity.class);
  }

  @Override
  public int getBindingVariable() {
    return BR.viewModel;
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_main;
  }

  @Override
  public MainViewModel getViewModel() {
    mMainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);
    return mMainViewModel;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mActivityMainBinding = getViewDataBinding();
    mMainViewModel.setNavigator(this);
    setUp();
  }

  @Override public void handleError(Throwable throwable) {

  }

  private void setUp() {
    fragmentManager = getSupportFragmentManager();

    mDrawer = mActivityMainBinding.drawerView;
    Toolbar mToolbar = mActivityMainBinding.toolbar;
    mNavigationView = mActivityMainBinding.navigationView;

    mToolbar.setTitle("Github API");
    mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

    setSupportActionBar(mToolbar);
    ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
        this,
        mDrawer,
        mToolbar,
        R.string.open_drawer,
        R.string.close_drawer) {
      @Override
      public void onDrawerClosed(View drawerView) {
        super.onDrawerClosed(drawerView);
      }

      @Override
      public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
        hideKeyboard();
      }
    };
    mDrawer.addDrawerListener(mDrawerToggle);
    mDrawerToggle.syncState();
    setupNavMenu();
    mMainViewModel.onNavMenuCreated();
  }

  private void setupNavMenu() {
    NavHeaderBinding navHeaderBinding = DataBindingUtil.inflate(getLayoutInflater(),
        R.layout.nav_header, mActivityMainBinding.navigationView, false);
    mActivityMainBinding.navigationView.addHeaderView(navHeaderBinding.getRoot());
    navHeaderBinding.setViewModel(mMainViewModel);

    mNavigationView.setNavigationItemSelectedListener(
        item -> {
          if (lastMenu == item.getItemId()) {
            return false;
          }
          this.lastMenu = item.getItemId();
          mDrawer.closeDrawer(GravityCompat.START);
          switch (item.getItemId()) {
            case R.id.nav_repositories:
              loadFragment(RepositoryFragment.newInstance());
              return true;
            default:
              return false;
          }
        });
    this.lastMenu = R.id.nav_repositories;
    mNavigationView.setCheckedItem(R.id.nav_repositories);
    loadFragment(RepositoryFragment.newInstance());
  }

  private void loadFragment(Fragment fragment) {
    if (fragment != null) {
      String fragmentTag = fragment.getClass().getName();

      FragmentTransaction transaction = fragmentManager.beginTransaction();
      transaction.replace(R.id.fragment_container, fragment, fragmentTag);
      transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

      if (fragmentManager.findFragmentByTag(fragmentTag) == null) {
        transaction.addToBackStack(fragmentTag);
      }
      transaction.commit();
    }
  }
}
