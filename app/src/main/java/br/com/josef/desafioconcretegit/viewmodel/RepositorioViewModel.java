package br.com.josef.desafioconcretegit.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.josef.desafioconcretegit.model.data.repository.GitRepository;
import br.com.josef.desafioconcretegit.model.pojo.pull.PullRequest;
import br.com.josef.desafioconcretegit.model.pojo.repositories.GitResult;
import br.com.josef.desafioconcretegit.model.pojo.repositories.Item;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RepositorioViewModel extends AndroidViewModel {

    private MutableLiveData<List<Item>> listaDeRepositorios = new MutableLiveData<>();
    private GitRepository gitRepository = new GitRepository();
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<List<PullRequest>> requestList = new MutableLiveData<>();
    private MutableLiveData<Boolean> booleano = new MutableLiveData<>();

    public RepositorioViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Item>> getListaRespositorios() {
        return this.listaDeRepositorios;
    }

    public LiveData<List<PullRequest>> getRequest() {
        return this.requestList;
    }

    public LiveData<Boolean> getBooleano() {
        return this.booleano;
    }



    public void getRepositorios(int page) {

        disposable.add(
                gitRepository.getRepositorios(page)

                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> booleano.setValue(true))
                        .doOnTerminate(() -> booleano.setValue(false))
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

    public void getPullRequest(String creatorString, String repoString) {
        disposable.add(
                gitRepository.getPullRequest(creatorString, repoString)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> booleano.setValue(true))
                        .doOnTerminate(() -> booleano.setValue(false))
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