package br.com.appdesafio.view.activity;

import android.content.BroadcastReceiver;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import br.com.appdesafio.R;
import br.com.appdesafio.databinding.ActivityBaseBinding;
import br.com.appdesafio.view.adapter.ListPullRequestAdapter;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * activity base, responsible for injecting the activity.
 */
public abstract  class  BaseActivity extends DaggerAppCompatActivity {



    @Inject
    public DispatchingAndroidInjector<Fragment> dispatchingFragmentInjector;

    //ActivityBaseBinding mActivityMainBinding;
   // Binding activityBaseBinding;


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(getLayout());
       // activityBaseBinding = DataBindingUtil.setContentView(this, getLayout());
       // configureToolbar();
        AndroidInjection.inject(this);

    }

/*    public void configureToolbar() {
        Toolbar toolbar = mActivityMainBinding.toolbarBase;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle(getTitleToolbar());

    }*/


/*    protected abstract String getTitleToolbar();
    protected abstract int getLayout();*/


    //public void configureRecyclerView(){
   /*     recyclerView = mBinding.recyclerView;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                //addDataToList(page);
            }
        };
        // Adds the scroll listener to RecyclerView
        recyclerView.addOnScrollListener(scrollListener);

        adapter = new ListPullRequestAdapter(this);*/
  //  }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingFragmentInjector;
    }










}

