package com.felipemiranda.desafioconcrete.ui.home.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.AnimRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.felipemiranda.desafioconcrete.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by felipemiranda
 */

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setTitle(getString(R.string.github_java));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, HomeFragment.newInstance(), HomeFragment.TAG)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showLoading(){
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideLoading(){
        progressBar.setVisibility(View.GONE);
    }

    public void addFragmentDefaultAnim(Fragment fragment, String tag, String tagBackStack) {
        addFragment(fragment, tag, R.anim.slide_from_right, R.anim.slide_to_left, R.anim.slide_from_left, R.anim.slide_to_right, tagBackStack);
    }

    private void addFragment(Fragment fragment, String tag,
                             @AnimRes int enter, @AnimRes int exit,
                             @AnimRes int popEnter, @AnimRes int popExit,
                             String tagBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();

        transaction.setCustomAnimations(enter, exit, popEnter, popExit);
        transaction.replace(R.id.content, fragment, tag);
        transaction.addToBackStack(tagBackStack);
        transaction.commit();
    }
}
