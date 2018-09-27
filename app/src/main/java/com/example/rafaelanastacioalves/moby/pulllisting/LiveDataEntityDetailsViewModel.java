package com.example.rafaelanastacioalves.moby.pulllisting;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.rafaelanastacioalves.moby.vo.Pull;
import com.example.rafaelanastacioalves.moby.repository.GitHubRepository;

import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class LiveDataEntityDetailsViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Pull>> mPullList = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Pull>> getEntityDetails() {
        return mPullList;
    }

    public void loadData(String mCreatorString, String mRepositoryString) {
        Timber.i("LiveDataEntityDetailsViewModel loadData");

        GitHubRepository repository = new GitHubRepository();
        Single<ArrayList<Pull>> observable = repository.getPullList(mCreatorString, mRepositoryString);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            mPullList.setValue(response);
                        },
                        throwable -> throwable.printStackTrace());

    }

}

