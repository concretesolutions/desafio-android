package com.example.rafaelanastacioalves.moby.repository;

import com.example.rafaelanastacioalves.moby.vo.Pull;
import com.example.rafaelanastacioalves.moby.vo.Repo;
import com.example.rafaelanastacioalves.moby.vo.RepoContainer;
import com.example.rafaelanastacioalves.moby.api.APIClient;
import com.example.rafaelanastacioalves.moby.api.ServiceGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitHubRepository {

    public Single<List<Repo>> getRepoList(String page, String gitRepoLanguage, String gitSortParam) {

        return Single.create(emitter -> {
            //synchronous logic in a thread apart
            RepoContainer repoContainerFromDB = DBHelper.getRepoContainerOfPage(page);

            // cache
            if (repoContainerFromDB != null
                    && repoContainerFromDB.getRepoList() != null
                    && !repoContainerFromDB.getRepoList().isEmpty()
                    ) {
                emitter.onSuccess(repoContainerFromDB.getRepoList());
                return;
            }

            // not cached...
                APIClient apiClient = ServiceGenerator.createService(APIClient.class);
                Call<RepoContainer> retrofitCall = apiClient.getRepos("language:" + gitRepoLanguage,
                        gitSortParam,
                        page
                );

                retrofitCall.enqueue(new Callback<RepoContainer>() {
                    @Override
                    public void onResponse(Call<RepoContainer> call, Response<RepoContainer> response) {
                        if (response.isSuccessful()) {
                            RepoContainer repoContainer = response.body();
                            DBHelper.setRepoContainerOfPage(String.valueOf(page), repoContainer);

                            // here we get again from DB as we obey the "single source of truth" approach
                            RepoContainer finalRepoContainerFromDB = DBHelper.getRepoContainerOfPage(page);
                            emitter.onSuccess(finalRepoContainerFromDB.getRepoList());

                        } else {

                            //we emmit 4xx and 5xx error messages
                            try {
                                String errorString = response.errorBody().string();
                                emitter.onError(new Exception(errorString));
                            } catch (IOException e) {
                                emitter.onError(e.getCause());
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<RepoContainer> call, Throwable t) {

                        // IO error cases
                        emitter.onError(t);
                    }
                });


        });


    }

    public Single<ArrayList<Pull>> getPullList(String mCreatorString, String mRepositoryString) {

        return Single.create(emitter -> {

            ArrayList<Pull> pullListFromDB = DBHelper.getPullListForRepo(mCreatorString, mRepositoryString);

            // with cache
            if (pullListFromDB != null) {
                emitter.onSuccess(pullListFromDB);
                return;
            }

            // retrieving from HTTP Repository

            // not cached...
            try {
                APIClient apiClient = ServiceGenerator.createService(APIClient.class);
                Call<ArrayList<Pull>> retrofitCall = apiClient.getPulls(mCreatorString, mRepositoryString);


                Response<ArrayList<Pull>> response = retrofitCall.execute();
                if (response.isSuccessful()) {
                    ArrayList<Pull> pullList = response.body();
                    DBHelper.setPullListForRepo(mCreatorString, mRepositoryString, pullList);

                    // here we get again from DB as we obey the "single source of truth" approach
                    pullListFromDB = DBHelper.getPullListForRepo(mCreatorString, mRepositoryString);
                    if (pullListFromDB == null) {
                        pullListFromDB = new ArrayList<Pull>();
                    }
                    emitter.onSuccess(pullListFromDB);

                } else {

                    //we emmit 4xx and 5xx error messages
                    emitter.onError(new Throwable(response.errorBody().string()));
                }
            } catch (IOException e) {

                //we emmit connection (IO) errors
                emitter.onError(e);
            }
        });
    }
}
