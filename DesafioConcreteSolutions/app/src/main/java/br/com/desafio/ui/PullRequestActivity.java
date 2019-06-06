package br.com.desafio.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import br.com.desafio.R;
import br.com.desafio.common.Connection;
import br.com.desafio.common.Load;
import br.com.desafio.common.State;
import br.com.desafio.controller.PullRequestController;
import br.com.desafio.domain.PullRequest;
import br.com.desafio.ui.adapter.PullRequestAdapter;

@EActivity(R.layout.pull_request_activity)
public class PullRequestActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    @Extra
    @InstanceState
    ArrayList<PullRequest> pullRequesties;
    @Bean
    PullRequestController pullRequestController;
    @Extra
    String owner;
    @Extra
    String repository;
    @ViewById(R.id.pullRequests)
    RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    @Bean
    PullRequestAdapter pullRequestAdapter;
    Load load;
    @InstanceState
    State estado;
    @ViewById
    TextView countPulRequestOpened;
    @ViewById
    TextView countPulRequestClosed;
    @ViewById
    TextView separator;
    @StringRes(R.string.without_connection)
    String semConexao;
    @StringRes(R.string.opened)
    String closed;
    @StringRes(R.string.closed)
    String opened;

    @AfterViews
    void afterViews(){
        EventBus.getDefault().register(this);
        this.load = new Load(this);

        if(estado == null)
            this.estado = State.INITIAL;

        getSupportActionBar().setTitle(repository);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.layoutManager = new LinearLayoutManager(recyclerView.getContext());
        this.recyclerView.setLayoutManager(layoutManager);

        if(this.estado.equals(State.INITIAL) && pullRequesties == null){
            if (Connection.isOnline(this)) {
                this.pullRequestController.findPullRequesties(owner, repository);
                this.load.show();
            } else {
                Toast.makeText(this, semConexao, Toast.LENGTH_SHORT).show();
            }
        }else{
            this.initListView();
            this.initOpneCloseCount();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWebClientPullRequest(List<PullRequest> pullRequesties){
        this.load.hide();
        this.pullRequesties = (ArrayList<PullRequest>) pullRequesties;
        this.initListView();
        this.initOpneCloseCount();
        this.estado = State.FINISHED;
    }

    private void initOpneCloseCount() {
        int countOpen = 0;
        int countClose = 0;
        for (PullRequest pullRequest : pullRequesties) {
            if(pullRequest.getState().equals("open")){
                countOpen++;
            }else{
                countClose++;
            }
        }
        separator.setVisibility(View.VISIBLE);
        countPulRequestOpened.setText(String.valueOf(countOpen) + opened);
        countPulRequestClosed.setText(String.valueOf(countClose) + closed);
    }

    void initListView(){
        this.pullRequestAdapter.setItems(this.pullRequesties);
        this.pullRequestAdapter.setOnItemClickListener(this);
        this.recyclerView.setAdapter(pullRequestAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWebClientError(final String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        this.estado = State.FINISHED;
        this.load.hide();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(Connection.isOnline(this)) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(pullRequesties.get(position).getUrl()));
            startActivity(intent);
        }else{
            Toast.makeText(this, semConexao, Toast.LENGTH_SHORT).show();
        }
    }

    @OptionsItem(android.R.id.home)
    void back() {
       finish();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}