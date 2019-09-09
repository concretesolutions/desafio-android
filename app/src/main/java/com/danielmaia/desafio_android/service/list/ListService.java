package com.danielmaia.desafio_android.service.list;

import android.util.Log;

import com.danielmaia.desafio_android.AppRepo;
import com.danielmaia.desafio_android.R;
import com.danielmaia.desafio_android.http.RetrofitConfig;
import com.danielmaia.desafio_android.listeners.AppListeners;
import com.danielmaia.desafio_android.model.Owner;
import com.danielmaia.desafio_android.model.Repo;
import com.danielmaia.desafio_android.service.list.dto.RepoDto;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;


public class ListService {
    private String error = AppRepo.getInstance().getResources().getString(R.string.error_service);
    private static final String TAG = "ListService";

    public interface ListServiceRequest {
        @GET("search/repositories?q=language:Java&sort=stars")
        Observable<ListResponse> getJavaList(@Query("page") int page);
    }

    public ListService(int page) {
        Observable<ListResponse> observable = new RetrofitConfig()
                .getJavaList()
                .getJavaList(page);

        observable.subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<ListResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "error: " + e.getMessage());
                        AppListeners.getInstance().notifyOnListError(error);
                    }

                    @Override
                    public void onNext(ListResponse listResponse) {
                        if (listResponse.getItems() != null) {
                            for (RepoDto repoDto : listResponse.getItems()) {
                                Repo repo = Repo.findByGuid(repoDto.getId());

                                if (repo == null)
                                    repo = new Repo();

                                repo.setGuid(repoDto.getId());
                                repo.setName(repoDto.getName());
                                repo.setDescription(repoDto.getDescription());
                                repo.setStargazers_count(repoDto.getStargazers_count());
                                repo.setForks(repoDto.getForks());

                                if (repoDto.getOwner() != null){
                                    Owner owner = Owner.findByGuid(repoDto.getOwner().getId());

                                    if (owner == null)
                                        owner = new Owner();

                                    owner.setGuid(repoDto.getOwner().getId());
                                    owner.setLogin(repoDto.getOwner().getLogin());
                                    owner.setAvatar_url(repoDto.getOwner().getAvatar_url());
                                    owner.save();

                                    repo.setOwner_id(owner.getGuid());

                                } else
                                    repo.setOwner_id(0);

                                repo.save();
                            }
                        }
                        AppListeners.getInstance().notifyOnListSuccess(listResponse.getTotal_count() > Repo.findAll().size());
                    }
                });
    }
}





