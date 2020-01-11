package com.alexandreobs.testeconcrete.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alexandreobs.testeconcrete.model.data.repository.GitRepository;
import com.alexandreobs.testeconcrete.model.pojo.repositories.GitResult;
import com.alexandreobs.testeconcrete.model.pojo.repositories.Item;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RepositorioViewModel extends AndroidViewModel {

    private MutableLiveData<List<Item>> listaDeRepositorios = new MutableLiveData<>();
    private GitRepository gitRepository = new GitRepository();
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();


    public RepositorioViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Item>> getListaRespositorios(){
        return this.listaDeRepositorios;
    }

    public LiveData<Boolean> getLoading(){
        return this.loading;
    }

    public void getRepositorios(int pagina) {

        disposable.add(
                gitRepository.getRepo(pagina)

                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> loading.setValue(true))
                        .doAfterTerminate(()-> {
                            loading.setValue(false);
                        })
                        .subscribe(gitResult -> {
                                    listaDeRepositorios.setValue(gitResult.getItems());
                                },
                                throwable -> {
                                    Log.i("LOG", "Erro" + throwable.getMessage());
                                }
                        )
        );

    }
}

