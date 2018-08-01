package com.example.lucas.concrete_solutions_desafio.dao;

import android.content.Context;
import android.widget.Toast;
import com.example.lucas.concrete_solutions_desafio.contract.IPullRequest;
import com.example.lucas.concrete_solutions_desafio.contract.IRepository;
import com.example.lucas.concrete_solutions_desafio.contract.MainContract;
import com.example.lucas.concrete_solutions_desafio.contract.PullRequestContract;
import com.example.lucas.concrete_solutions_desafio.model.PullRequest;
import com.example.lucas.concrete_solutions_desafio.model.PullRequestList;
import com.example.lucas.concrete_solutions_desafio.model.RepositoryList;
import com.example.lucas.concrete_solutions_desafio.presenter.MainActivityPresenter;
import com.example.lucas.concrete_solutions_desafio.presenter.PullRequestPresenter;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiDao {
    final MainContract.Presenter  mainPresenter;
    final PullRequestContract.Presenter pullRequestPresenter;


    public ApiDao(MainContract.Presenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        this.pullRequestPresenter = null;
    }

    public ApiDao(PullRequestContract.Presenter pullRequestPresenter) {
        this.mainPresenter = null;
        this.pullRequestPresenter = pullRequestPresenter;
    }

    public void repositoryRequisition (final int pageNumber, final Context context){
        final int first = 1;
        IRepository gitHubRepository = IRepository.retrofit.create(IRepository.class);
        final retrofit2.Call<RepositoryList> call = gitHubRepository.getRepositories(Integer.toString(pageNumber));
        final RepositoryList repositories = new RepositoryList();

        call.enqueue(new Callback<RepositoryList>() {
            @Override
            public void onResponse(Call<RepositoryList> call, Response<RepositoryList> response) {
                int code = response.code();
                if (code == 200){
                    repositories.setRepositories(response.body());

                    if(pageNumber==first) {
                        mainPresenter.fillScreenPresenter(repositories);
                    }
                    else{
                        mainPresenter.refillScreenPresenter(repositories);
                    }

                }
                else{

                    Toast.makeText(context, "Nós tivemos um pequeno problema ao carregar a lista de repositórios. Por favor reinicie o app :)\nErro:" +String.valueOf(code),
                            Toast.LENGTH_LONG).show();
                    mainPresenter.interruptProgressBar();
                }
            }

            @Override
            public void onFailure(Call<RepositoryList> call, Throwable t) {
                Toast.makeText(context, "Nós tivemos um pequeno problema ao carregar os repositórios. Por favor reinicie o app  e se certifique do acesso a internet :)\nErro: onFailure",
                        Toast.LENGTH_LONG).show();
                mainPresenter.interruptProgressBar();
            }
        });

        return;
    }



    public void pullRequestRequisition (String repository_full_name, final Context context){

        IPullRequest gitHubPullRequest = IPullRequest.retrofit.create(IPullRequest.class);
        final retrofit2.Call<ArrayList<PullRequest>> call = gitHubPullRequest.getPullRequests(repository_full_name);
        final PullRequestList pullRequests = new PullRequestList();
        final ArrayList<PullRequest> pullRequestList = new ArrayList();
        call.enqueue(new Callback<ArrayList<PullRequest>>() {

            @Override
            public void onResponse(Call<ArrayList<PullRequest>> call, Response<ArrayList<PullRequest>> response) {
                int code = response.code();
                if (code == 200){
                    pullRequestList.addAll(response.body());
                    if(pullRequestList.size()!=0){
                        pullRequests.setPullRequests(pullRequestList);
                        pullRequestPresenter.fillScreenPresenter(pullRequests);
                    }
                    else {
                        pullRequestPresenter.fillScreenPresenter(pullRequests);
                    }
                }
                else{
                    Toast.makeText(context, "Nós tivemos um pequeno problema ao carregar os pullrequests. Por favor reinicie o app :)\nErro:" +String.valueOf(code),
                            Toast.LENGTH_LONG).show();
                    pullRequestPresenter.interruptProgressBar();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PullRequest>> call, Throwable t) {
                Toast.makeText(context, "Nós tivemos um pequeno problema ao carregar os pullrequests. Por favor reinicie o app e se certifique do acesso a internet :)\nErro: onFailure",
                        Toast.LENGTH_LONG).show();
                pullRequestPresenter.interruptProgressBar();
            }
        });

    return;
    }
}
