package br.com.concrete.desafio_android.presenter;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.concrete.desafio_android.R;
import br.com.concrete.desafio_android.data.external.RepositoryRequest;
import br.com.concrete.desafio_android.domain.Repository;
import br.com.concrete.desafio_android.gui.RepositoryActivity;
import br.com.concrete.desafio_android.gui.adapter.RepositoryAdapter;
import br.com.concrete.desafio_android.util.Network;

import static br.com.concrete.desafio_android.util.Constants.LIST_VIEW_INSTANCE_STATE_KEY;

/**
 * Created by Daivid
 * Dedicated class to load and manipulate data on activities
 *      also constraint and logical procedures needed to be done
 * Here we use a singleton to not be necessary recreate
 *      the same object more than one time
 */
public class RepositoryPresenter  {

    private static final RepositoryPresenter INSTANCE = new RepositoryPresenter();
    private RepositoryPresenter(){ }
    public static RepositoryPresenter getInstance(){
        return INSTANCE;
    }
    private Network network = new Network();
    private RepositoryActivity repositoryActivity = null;
    private String language = "language:java";

    public boolean loadProgress = false;
    public ProgressDialog progressDialog = null;

    /**
     * Load the first elements from the repository
     * Call the api to catch the list of objects
     */
    public void loadListView(){
        if(network.isConnected(repositoryActivity)){
            this.loadProgress();
            RepositoryRequest.getInstance().loadRepositories(language,1);
        }
    }

    /**
     * Load the pageNumber elements from the repository
     * Call the api to catch the list of objects
     */
    public void loadMore(int pageNumber){
        if(network.isConnected(repositoryActivity)){
            this.loadProgress();
            RepositoryRequest.getInstance().loadRepositories(language, pageNumber);
        }
    }

    /** Initialize the data of the activity
     * verify if the user already charged it to not be necessary to load it from scratch
     * then if it's the first time it Load the first elements from the repository
     * Call the api to catch the list of objects
     */
    public void initializeData(Bundle savedState, String language, RepositoryActivity repositoryActivity){
        this.repositoryActivity = repositoryActivity;
        this.language = language;

        if(savedState != null){
            this.repositoryActivity.setRepositories((ArrayList<Repository>) savedState.getSerializable(LIST_VIEW_INSTANCE_STATE_KEY));
            this.updateUIList(this.repositoryActivity.getRepositories());
        }else{
            RepositoryPresenter.getInstance().loadListView();
        }
    }

    /**
     * resetAdapter and set new language type on the listview
     * @param language
     */
    public void reloadWithNewData(String language){
        this.repositoryActivity
                .setRepositoryAdapter(new RepositoryAdapter(R.layout.repository_item,
                        this.repositoryActivity,
                        new ArrayList<Repository>()));
        this.repositoryActivity
                .getRepositoryListView()
                .setAdapter(this.repositoryActivity.getRepositoryAdapter());
        this.language = language;
        this.loadListView();
    }

    /**
     * reload the listview with more repositories
     * @param repositories
     */
    public void updateUIList(ArrayList<Repository> repositories){
        repositoryActivity.setRepositories(repositories);
        repositoryActivity.getRepositoryAdapter().addAll(repositories);
        repositoryActivity.getRepositoryAdapter().notifyDataSetChanged();
        this.dismissProgress();
    }

    /**
     * log any message to the user
     * @param id
     */
    public void logMessageToUser(int id){
        Toast.makeText(repositoryActivity, repositoryActivity.getResources().getText(id), Toast.LENGTH_LONG).show();
        this.dismissProgress();
    }

    /**
     * Function used to show a progress and block other functionalities
     */
    public void loadProgress(){
        ProgressDialog progress = new ProgressDialog(this.repositoryActivity);
        progress.setMessage(this.repositoryActivity.getResources().getString(R.string.loading));
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
