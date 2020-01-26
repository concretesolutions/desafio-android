package br.com.victoramaral.githubdive.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.victoramaral.githubdive.model.data.repository.RequestReturn;
import br.com.victoramaral.githubdive.model.pojos.requests.Request;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RequestViewModel extends AndroidViewModel {

    private MutableLiveData<List<Request>> pullRequest = new MutableLiveData<>();
    private RequestReturn repository = new RequestReturn();
    private CompositeDisposable disposable = new CompositeDisposable();

    public RequestViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Request>> getPullRequest() {
        return this.pullRequest;
    }

    public void getAllRequests(String owner, String repo) {
        disposable.add(
                repository.getRequests(owner, repo)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(requests -> {
                            pullRequest.setValue(requests);
                        }, throwable -> {
                            Log.i("LOG", "Error " + throwable.getMessage());
                        })
        );
    }

    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
