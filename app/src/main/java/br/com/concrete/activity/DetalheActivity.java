package br.com.concrete.activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.List;
import br.com.concrete.R;
import br.com.concrete.adapter.PullRequestAdapter;
import br.com.concrete.base.BaseActivity;
import br.com.concrete.model.RetornoPullRequest;
import br.com.concrete.util.Constantes;
import br.com.concrete.util.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalheActivity extends BaseActivity {

    private TextView texto_barra;
    private ImageButton back;
    private RecyclerView listPR;
    private LinearLayoutManager layoutManager;
    private PullRequestAdapter pullRequestAdapter;
    private static Bundle mBundleRecyclerViewState;
    private final String LIST_STATE_KEY = "recycler_state";

    private String name;
    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_detalhe);
        init();
        actionBotaoVoltar();
    }

    private void init(){
        context = this;
        name = getIntent().getStringExtra("name");
        login = getIntent().getStringExtra("login");
        back = findViewById(R.id.back);
        texto_barra = findViewById(R.id.texto);
        texto_barra.setText("Detalhamento");
        listPR = findViewById(R.id.listPR);
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        listPR.setLayoutManager(layoutManager);
        Utils.initImageLoader(this);
        getPullRequest();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBundleRecyclerViewState = new Bundle();
        Parcelable listState = listPR.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable(LIST_STATE_KEY, listState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mBundleRecyclerViewState != null) {
            Parcelable listState = mBundleRecyclerViewState.getParcelable(LIST_STATE_KEY);
            listPR.getLayoutManager().onRestoreInstanceState(listState);
        }
    }

    private void actionBotaoVoltar(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void getPullRequest(){
        onShowProgressDialog(Constantes.AGUARDE);
        Call<List<RetornoPullRequest>> listCall = restApi.getPullRequests(login, name);
        listCall.enqueue(new Callback<List<RetornoPullRequest>>() {
            @Override
            public void onResponse(Call<List<RetornoPullRequest>> call, Response<List<RetornoPullRequest>> response) {
                onDismissProgressDialog();
                if(response != null && response.body() != null && !response.body().isEmpty()){
                    populate(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<RetornoPullRequest>> call, Throwable t) {
                onDismissProgressDialog();
                Log.e("Error ",t.getMessage());
            }
        });
    }

    private void populate(List<RetornoPullRequest> lista){
        pullRequestAdapter = new PullRequestAdapter(this, lista);
        listPR.setAdapter(pullRequestAdapter);
    }
}