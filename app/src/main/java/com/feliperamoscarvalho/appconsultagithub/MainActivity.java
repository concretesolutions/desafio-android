package com.feliperamoscarvalho.appconsultagithub;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.feliperamoscarvalho.appconsultagithub.repositories.RepositoriesFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            addFragment(new RepositoriesFragment());
        }
    }

    /**
     * Add fragment to Frame Layout
     */
    private void addFragment (Fragment repositoriesFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content, repositoriesFragment);
        fragmentTransaction.commit();
    }
}
