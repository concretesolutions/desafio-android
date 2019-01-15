package br.com.appdesafio.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import javax.inject.Inject;
import br.com.appdesafio.model.pojo.Repository;
import br.com.appdesafio.repository.ListRepository;

public class ListRepositoryViewModel extends ViewModel {
    @Inject
    ListRepository mRepository;

    public Context mContext;

    public ListRepositoryViewModel(final ListRepository listRepository, final Application application){
        this.mRepository = listRepository;
        this.mContext = application;
    }

    /**
     * Search the server for the java repository list.
     * @return returns the repository list.
     */
    public LiveData<Repository> getListRepository(final int page) {
        return mRepository.getListRepository(page);


    }



}
