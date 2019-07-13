package com.felipe.palma.desafioconcrete.ui.repository;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.felipe.palma.desafioconcrete.R;
import com.felipe.palma.desafioconcrete.domain.model.Item;
import com.felipe.palma.desafioconcrete.domain.response.RepositoriesResponse;
import com.felipe.palma.desafioconcrete.network.IServiceGithub;
import com.felipe.palma.desafioconcrete.network.ServiceGithubImp;
import com.felipe.palma.desafioconcrete.ui.adapter.AnimationItem;
import com.felipe.palma.desafioconcrete.ui.adapter.RecyclerItemClickListener;
import com.felipe.palma.desafioconcrete.ui.adapter.RepositoryAdapter;
import com.felipe.palma.desafioconcrete.ui.adapter.decoration.ItemOffsetDecoration;
import com.felipe.palma.desafioconcrete.utils.Config;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Felipe Palma on 11/07/2019.
 */

public class RepositoriesActivity extends AppCompatActivity implements RepositoriesContract.View {

    private RepositoriesContract.Presenter mPresenter;
    private RepositoryAdapter mRepoAdapter;
    private int mPage = 1;
    private ProgressDialog dialog;

    private List<Item> mRepoList = new ArrayList<>();

    //Views

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        INSTANCIA BUTTERKNIFE
         */
        ButterKnife.bind(this);

        mPresenter = new RepositoriesPresenter(this);

        mPresenter.loadRepositories(mPage);

        setupRecyclerView();



    }

    @Override
    public void hideDialog() {
        dialog.hide();
    }

    @Override
    public void showDialog() {
        dialog = ProgressDialog.show(this, "Baixando dados",
                "Aguarde ...", true);

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showRepositories(List<Item> itens) {
        Log.d("REPO", itens.toString());
        mRepoList = itens;
        mRepoAdapter = new RepositoryAdapter(this, mRepoList, recyclerItemClickListener);
        mRecyclerView.setAdapter(mRepoAdapter);



    }

    /**
     * RecyclerItem click event listener
     * */
    private RecyclerItemClickListener recyclerItemClickListener = item -> {
        Toast.makeText(this,"Click", Toast.LENGTH_LONG).show();
//        Intent mIntent = new Intent(MainActivity.this,ProductDetailActivity.class);
//        mIntent.putExtra(Config.PRODUCT_ITEM,item);
//        startActivity(mIntent);
    };

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        AnimationItem mAnimationItem =  new AnimationItem("Slide from bottom", R.anim.layout_animation_from_bottom);
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, mAnimationItem.getResourceId());

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    private void setupRecyclerView() {
        final Context context = mRecyclerView.getContext();
        final int spacing = getResources().getDimensionPixelOffset(R.dimen.default_spacing_small);
        mRecyclerView.setLayoutManager(getLayoutManager(context));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new ItemOffsetDecoration(spacing));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private RecyclerView.LayoutManager getLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }
}
