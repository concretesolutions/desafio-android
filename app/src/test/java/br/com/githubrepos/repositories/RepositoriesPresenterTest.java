package br.com.githubrepos.repositories;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import br.com.githubrepos.data.entity.Repository;
import br.com.githubrepos.data.entity.RepositoryStatus;
import br.com.githubrepos.data.entity.User;
import br.com.githubrepos.data.service.RepositoryServiceApi;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class RepositoriesPresenterTest {

    private static List<Repository> REPOSITORY_LIST;
    private static RepositoryStatus REPOSITORY_STATUS;

    static {
        REPOSITORY_LIST = new ArrayList<>();
        for (int i = 0; i <= 30; i++) {
            User user = new User(i + 100, "userLogin" + i, "https://avatars2.githubusercontent.com/u/6407041?v=3",
                    "https://api.github.com/users/ReactiveX");

            REPOSITORY_LIST.add(new Repository(i, "repositoryName" + i, "ReactiveX/RxJava",
                    user, "Some description about this repository" + i, 31 * (i + 1), 5 * (i + 1)));
        }
        REPOSITORY_STATUS = new RepositoryStatus(REPOSITORY_LIST.size(), false, REPOSITORY_LIST);
    }

    @Mock
    private RepositoryServiceApi repositoryServiceApi;
    @Captor
    ArgumentCaptor<RepositoryServiceApi.RepositoryServiceCallback> repositoryServiceCallbackArgumentCaptor;

    @Mock
    private RepositoriesContract.View repositoriesView;
    private RepositoriesContract.UserActionsListener repositoriesPresenter;

    private int page;
    private String language, sort;
    private boolean doRefresh;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        repositoriesPresenter = new RepositoriesPresenter(repositoryServiceApi, repositoriesView);

        language = "language:Java";
        sort = "stars";
        page = 1;
        doRefresh = true;
    }

    @Test
    public void loadRepositoriesFromApiAndLoadIntoView() {
        repositoriesPresenter.loadRepositoryList(page, doRefresh);

        verify(repositoriesView).setProgressIndicator(true);
        verify(repositoryServiceApi).search(eq(page), eq(language), eq(sort), repositoryServiceCallbackArgumentCaptor.capture());
        repositoryServiceCallbackArgumentCaptor.getValue().onLoaded(REPOSITORY_STATUS);
        verify(repositoriesView).setProgressIndicator(false);
        verify(repositoriesView).showRepositoryList(REPOSITORY_STATUS.getRepositoryList(), doRefresh);
    }

    @Test
    public void loadRepositoriesFromApiWhenScrollAndLoadIntoView() {
        repositoriesPresenter.loadRepositoryList(++page, doRefresh = false);

        verify(repositoriesView).setListProgressIndicator(true);
        verify(repositoryServiceApi).search(eq(page), eq(language), eq(sort), repositoryServiceCallbackArgumentCaptor.capture());
        repositoryServiceCallbackArgumentCaptor.getValue().onLoaded(REPOSITORY_STATUS);
        verify(repositoriesView).setListProgressIndicator(false);
        verify(repositoriesView).showRepositoryList(REPOSITORY_STATUS.getRepositoryList(), doRefresh);
    }

    @Test
    public void clickOnRepository_ShowPullRequestsUi() {
        Repository repository = REPOSITORY_LIST.get(0);

        repositoriesPresenter.openRepository(repository);

        verify(repositoriesView).showPullRequestListUi(eq(repository.getOwner().getLogin()),
                eq(repository.getName()));
    }

    @Test
    public void longClickOnRepository_SelectItAndReplaceIconsOnActionBar() {
        repositoriesPresenter.selectRepository(0);

        verify(repositoriesView).changeActionBarWhenRepositorySelected();
    }

    @Test
    public void clickOnBackButton_UnselectRepository() {
        //pre-condition
        repositoriesPresenter.selectRepository(4);

        repositoriesPresenter.unselectRepository(4);
        verify(repositoriesView).changeActionBarWhenRepositoryUnselected(4);
    }

    @Test
    public void clickOnDeleteButton_DeleteRepository() {
        //precondition
        repositoriesPresenter.selectRepository(0);

        repositoriesPresenter.deleteSelectedRepository();
        verify(repositoriesView).changeActionBarWhenRepositoryUnselected(0);
        verify(repositoriesView).removeRepository(0);
    }

}
