package br.com.githubrepos;

import br.com.githubrepos.data.service.impl.FakePullRequestServiceApiImpl;
import br.com.githubrepos.data.service.impl.FakeRepositoryServiceApiImpl;

public class Injection {

    public static FakeRepositoryServiceApiImpl provideRepositoryServiceApi() {
        return new FakeRepositoryServiceApiImpl();
    }

    public static FakePullRequestServiceApiImpl providePullRequestServiceApi() {
        return new FakePullRequestServiceApiImpl();
    }

}
