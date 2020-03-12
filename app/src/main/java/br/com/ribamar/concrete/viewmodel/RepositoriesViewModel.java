package br.com.ribamar.concrete.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import java.util.List;

import br.com.ribamar.concrete.model.data.repository.RepositoryReturn;
import br.com.ribamar.concrete.model.pojos.repositories.Item;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RepositoriesViewModel extends AndroidViewModel {

    private MutableLiveData<List<Item>> listaRepositories = new MutableLiveData<>();
    private RepositoryReturn repository = new RepositoryReturn();
    private CompositeDisposable disposable = new CompositeDisposable();

    public RepositoriesViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Item>> getListaRepositories() {
        return this.listaRepositories;
    }

    public void getAllRepositories(int page, int perPage) {
        disposable.add(
                repository.getRepositories(page, perPage)
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
