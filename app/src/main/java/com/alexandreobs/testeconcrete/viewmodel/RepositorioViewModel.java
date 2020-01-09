package com.alexandreobs.testeconcrete.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alexandreobs.testeconcrete.model.data.repository.GitRepository;
import com.alexandreobs.testeconcrete.model.pojo.GitResult;
import com.alexandreobs.testeconcrete.model.pojo.Item;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RepositorioViewModel extends AndroidViewModel {

    private MutableLiveData<List<Item>> listaDeRepositorios = new MutableLiveData<>();
    private GitRepository gitRepository = new GitRepository();
    private CompositeDisposable disposable = new CompositeDisposable();

    public RepositorioViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Item>> getListaRespositorios(){
        return this.listaDeRepositorios;
    }

    public void getRepositorios() {

        disposable.add(
                gitRepository.getRepo(/*"name", "login", "full_name", "escription",
                        "forks_count", "stargazers_count"*/)

                        .subscribeOn(Schedulers.newThread())

                        .observeOn(AndroidSchedulers.mainThread())

                        .subscribe(new Consumer<GitResult>() {
                            @Override
                            public void accept(GitResult gitResult) throws Exception {

                                listaDeRepositorios.setValue(gitResult.getItems());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                                Log.i("LOG", "Error: " + throwable.getMessage());
                            }
                        }));
    }
}

