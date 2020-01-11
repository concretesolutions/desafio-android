package com.alexandreobs.testeconcrete.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alexandreobs.testeconcrete.model.data.repository.RequestsRepository;
import com.alexandreobs.testeconcrete.model.pojo.pull.Request;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RequestViewModel extends AndroidViewModel {

    private MutableLiveData<List<Request>> requestList = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private RequestsRepository repository = new RequestsRepository();

    public RequestViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Request>> getRequest() {
        return this.requestList;
    }

    public LiveData<Boolean> getLoading() {
        return this.loading;
    }


    public void getPullRequest(String creatorString, String repoString, int pagina) {
        disposable.add(
                repository.getRequest(creatorString, repoString, pagina)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> loading.setValue(true))
                        .doOnTerminate(() -> loading.setValue(false))
                        .subscribe(request1 -> {
                            requestList.setValue(request1);
                        }, throwable -> {

                            Log.i("Log", "erro " + throwable.getMessage());
                        })


        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
