package br.com.concrete.desafio_android.presenter;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.concrete.desafio_android.R;
import br.com.concrete.desafio_android.data.external.PullRequest;
import br.com.concrete.desafio_android.domain.Pull;
import br.com.concrete.desafio_android.domain.Repository;
import br.com.concrete.desafio_android.gui.PullActivity;
import br.com.concrete.desafio_android.gui.adapter.PullAdapter;
import br.com.concrete.desafio_android.util.Network;

import static br.com.concrete.desafio_android.util.Constants.PULL_LIST_VIEW_INSTANCE_STATE_KEY;
import static br.com.concrete.desafio_android.util.Constants.TYPE_STATE_OPEN;

/**
 * Created by Daivid
 * Dedicated class to load and manipulate data on activities
 *      also constraint and logical procedures needed to be done
 * Here we use a singleton to not be necessary recreate
 *      the same object more than one time
 */
public class PullPresenter {

    private static final PullPresenter INSTANCE = new PullPresenter();
    private PullPresenter(){ }
    public static PullPresenter getInstance(){
        return INSTANCE;
    }
    private Network network = new Network();
    private Repository repository = null;
    private PullActivity pullActivity = null;

    public boolean loadProgress = false;
    public ProgressDialog progressDialog = null;

    /**
     * load list from external repository
     * @param pullActivity
     * @param repository
     */
    public void initializeData(PullActivity pullActivity, Repository repository, Bundle savedState){
        this.pullActivity = pullActivity;
        this.repository = repository;
        this.pullActivity.getRepositoryName().setText(repository.getName());

        if(savedState != null){
            this.pullActivity.setPullRequests((ArrayList<Pull>) savedState.getSerializable(PULL_LIST_VIEW_INSTANCE_STATE_KEY));
            updateUIList(this.pullActivity.getPullRequests());
        }else{
            PullPresenter.getInstance().loadPullRequests();
        }
    }

    private void loadPullRequests(){
        if(network.isConnected(pullActivity.getApplicationContext())){
            this.loadProgress();
            PullRequest.getInstance().loadPullRequests(repository.getOwner().getLogin(), repository.getName());
        }
    }

    /**
     * reload the listview with more pullRequests
     * @param pullRequests
     */
    public void updateUIList(ArrayList<Pull> pullRequests){
        int open = 0;
        for (Pull p :
                pullRequests) {
            if(p.getState().equals(TYPE_STATE_OPEN)){
                open++;
            }
        }

        this.pullActivity.getOpen().setText(Integer.toString(open) + " " + this.pullActivity.getResources().getString(R.string.opened));
        this.pullActivity.getClose().setText(" / " + Integer.toString(this.pullActivity.getPullRequests().size()- open) + " " + this.pullActivity.getResources().getString(R.string.closed));
        this.pullActivity.getPullAdapter().addAll(pullRequests);
        this.pullActivity.getPullAdapter().notifyDataSetChanged();
        this.dismissProgress();
    }

    /**
     * log any message to the user
     * @param id
     */
    public void logMessageToUser(int id){
        Toast.makeText(this.pullActivity, this.pullActivity.getResources().getText(id), Toast.LENGTH_LONG).show();
        this.dismissProgress();
    }

    /**
     * Function used to show a progress and block other functionalities
     */
    public void loadProgress(){
        ProgressDialog progress = new ProgressDialog(this.pullActivity);
        progress.setMessage(this.pullActivity.getResources().getString(R.string.loading));
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.show();
        progress.setCancelable(true);
        progress.setCanceledOnTouchOutside(false);
        this.progressDialog = progress;

        final Thread t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;
                while(true) {
                    try {
                        if(loadProgress){
                            break;
                        }
                        sleep(1000);
                        jumpTime += 5;
                        progressDialog.setProgress(jumpTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }

    /**
     * It has to be called, after load progress to dismiss the dialog
     */
    private void dismissProgress() {
        if(this.progressDialog != null){
            this.loadProgress = true;
            this.progressDialog.dismiss();
        }
    }


}
