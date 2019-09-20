package br.com.concrete.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import br.com.concrete.R;
import br.com.concrete.activity.MainActivity;
import br.com.concrete.activity.SecondActivity;
import br.com.concrete.activity.ThirdActivity;
import br.com.concrete.util.DialogUtil;
import br.com.concrete.util.Utils;
import butterknife.Bind;

public class BaseDrawerActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Views
    @Bind(R.id.drawer_layout)
    protected DrawerLayout mDrawerLayout;

    @Bind(R.id.nav_view)
    protected NavigationView mNavigationView;

    protected ActionBarDrawerToggle mDrawerToggle;
    protected LinearLayout nav_1, nav_2, nav_3, nav_sair;

    // Utils
    protected Context context;

    protected void onCreate(Bundle savedInstanceState, int layoutId) {
        super.onCreate(savedInstanceState, layoutId);
        setupNavigationDrawer();
        Utils.initImageLoader(this);
        init();
        setOnClick();
    }

    private void init(){
        context = this;
        nav_1 = findViewById(R.id.nav_1);
        nav_2 = findViewById(R.id.nav_2);
        nav_3 = findViewById(R.id.nav_3);
        nav_sair = findViewById(R.id.nav_sair);
    }


    private void setOnClick(){
        nav_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, MainActivity.class));
            }
        });
        nav_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, SecondActivity.class));
            }
        });
        nav_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ThirdActivity.class));
            }
        });
        nav_sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil ds = new DialogUtil(context);
                ds.show(getSupportFragmentManager(), this.getClass().getName());
            }
        });
    }

    public void setupNavigationDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}