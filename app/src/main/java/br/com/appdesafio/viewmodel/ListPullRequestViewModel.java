package br.com.appdesafio.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import br.com.appdesafio.model.pojo.PullRequest;
import br.com.appdesafio.model.pojo.Repository;
import br.com.appdesafio.repository.ListRepository;

public class ListPullRequestViewModel extends ViewModel {

    @Inject
    ListRepository mRepository;

    public Context mContext;

    public ListPullRequestViewModel (final ListRepository listRepository, final Application application){
        this.mRepository = listRepository;
        this.mContext = application;
    }

    /**
     * access the repository to login.
     * @return
     */
    public LiveData<List<PullRequest>> getListPullRequest(final String creator, final String repository) {
        return mRepository.getListPullRequest(creator, repository);


    }
}
