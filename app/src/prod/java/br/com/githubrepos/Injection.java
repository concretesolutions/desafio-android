package br.com.githubrepos;

import br.com.githubrepos.data.service.impl.PullRequestServiceApiImpl;
import br.com.githubrepos.data.service.impl.RepositoryServiceApiImpl;

public class Injection {

    public static RepositoryServiceApiImpl provideRepositoryServiceApi() {
        return new RepositoryServiceApiImpl();
    }

    public static PullRequestServiceApiImpl providePullRequestServiceApi() {
        return new PullRequestServiceApiImpl();
    }
}