package com.danielmaia.desafio_android.service.pull;

import android.util.Log;

import com.danielmaia.desafio_android.AppRepo;
import com.danielmaia.desafio_android.R;
import com.danielmaia.desafio_android.http.RetrofitConfig;
import com.danielmaia.desafio_android.listeners.AppListeners;
import com.danielmaia.desafio_android.model.Owner;
import com.danielmaia.desafio_android.model.Pull;
import com.danielmaia.desafio_android.service.pull.dto.PullDto;
import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;

public class PullService  {

    private String error = AppRepo.getInstance().getResources().getString(R.string.error_service);
    private static final String TAG = "PullService";

    public interface ListPullRequest {
        @GET("repos/{owner}/{repo}/pulls")
        Observable<List<PullDto>> getPullRequest(@Path("owner") String owner,
                                           @Path("repo") String repo);
    }

    public PullService(String owner, String repo) {

        Observable<List<PullDto>> observable = new RetrofitConfig()
                .getPullList()
                .getPullRequest(owner, repo);

        observable.subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<List<PullDto>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "error: " + e.getMessage());
                        AppListeners.getInstance().notifyOnPullError(error);
                    }

                    @Override
                    public void onNext(List<PullDto> pullDtos) {
                        if (pullDtos != null) {
                            Log.d(TAG, "sucess: " + pullDtos);

                            for (PullDto pullDto : pullDtos) {
                                Pull pull = Pull.findByGuid(pullDto.getId());

                                if (pull == null)
                                    pull = new Pull();

                                pull.setGuid(pullDto.getId());
                                pull.setTitle(pullDto.getTitle());
                                pull.setBody(pullDto.getBody());
                                pull.setState(pullDto.getState());
                                pull.setUrl(pullDto.getHtml_url());

                                if (pullDto.getUser() != null){
                                    Owner owner = Owner.findByGuid(pullDto.getUser().getId());

                                    if (owner == null)
                                        owner = new Owner();

                                    owner.setGuid(pullDto.getUser().getId());
                                    owner.setLogin(pullDto.getUser().getLogin());
                                    owner.setAvatar_url(pullDto.getUser().getAvatar_url());
                                    owner.save();

                                    pull.setOwner_id(owner.getGuid());

                                } else
                                    pull.setOwner_id(0);

                                pull.save();
                            }
                        }
                        AppListeners.getInstance().notifyOnPullSuccess();
                    }
                });
    }
}
