package br.com.victoramaral.githubdive.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.victoramaral.githubdive.model.data.apiReturn.RepositoryReturn;
import br.com.victoramaral.githubdive.model.pojos.repositories.Item;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PrincipalViewModel extends AndroidViewModel {

    private MutableLiveData<List<Item>> listaRepositories = new MutableLiveData<>();
    private RepositoryReturn repository = new RepositoryReturn();
    private CompositeDisposable disposable = new CompositeDisposable();

    public PrincipalViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Item>> getListaRepositories() {
        return this.listaRepositories;
    }

    public void getAllRepositories(int page) {
        disposable.add(
                repository.getRepositories(page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(repositories ->
                                listaRepositories.setValue(repositories.getItems()
                                ), throwable ->
                                Log.i("LOG", "Erro " + throwable.getMessage()))
        );
    }

    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}