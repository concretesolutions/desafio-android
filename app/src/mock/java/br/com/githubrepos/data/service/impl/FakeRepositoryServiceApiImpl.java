package br.com.githubrepos.data.service.impl;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import br.com.githubrepos.data.entity.Repository;
import br.com.githubrepos.data.entity.RepositoryStatus;
import br.com.githubrepos.data.entity.User;
import br.com.githubrepos.data.service.RepositoryServiceApi;

public class FakeRepositoryServiceApiImpl implements RepositoryServiceApi {

    private final static List<Repository> REPOSITORY_LIST;
    private static RepositoryStatus REPOSITORY_STATUS;

    static {
        REPOSITORY_LIST = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            User user = new User(i, "userLogin" + i, "https://avatars2.githubusercontent.com/u/6407041?v=3",
                    "https://api.github.com/users/ReactiveX");

            REPOSITORY_LIST.add(new Repository(i + 100, "repositoryName" + i, "ReactiveX/RxJava",
                    user, "Some description about this repository" + i, 31 * (i + 1), 5 * (i + 1)));
        }
    }

    @Override
    public void search(int page, String language, String sort,
                       final RepositoryServiceCallback<RepositoryStatus> callback) {

        page = page - 1;
        final int startIndex = page * 10;
        final int endIndex = startIndex + 10;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                try {
                    List<Repository> repositories = REPOSITORY_LIST.subList(startIndex, endIndex);
                    REPOSITORY_STATUS = new RepositoryStatus(repositories.size(), false, repositories);
                } catch (IndexOutOfBoundsException e) {
                    REPOSITORY_STATUS = new RepositoryStatus(0, false, new ArrayList<Repository>());
                }
                callback.onLoaded(REPOSITORY_STATUS);
            }
        }, 1500);
    }

}
