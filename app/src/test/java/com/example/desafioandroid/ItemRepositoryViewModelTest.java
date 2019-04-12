package com.example.desafioandroid;

import androidx.fragment.app.FragmentManager;
import com.example.desafioandroid.schema.RepositoryItem;
import com.example.desafioandroid.viewModel.itemAdapter.ItemRepositoryViewModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class) public class ItemRepositoryViewModelTest {

    private static final int REPOSITORY_FORK_TEST = 15006;
    private static final int REPOSITORY_WATCHER_TEST = 46477;
    private static final int REPOSITORY_LIKE_TEST = 4677;

    private FragmentManager mockContext = mock(FragmentManager.class);

    @Test
    public void shouldGetRepositoryFork() throws Exception {
        RepositoryItem repository = new RepositoryItem();
        repository.setForksCount(REPOSITORY_FORK_TEST);
        ItemRepositoryViewModel itemrepositoryViewModel = new ItemRepositoryViewModel(repository, mockContext);
        assertEquals(repository.getForksCount().toString(), itemrepositoryViewModel.cantFork());
    }

    @Test public void shouldGetRepositoryWatcher() throws Exception {
        RepositoryItem repository = new RepositoryItem();
        repository.setWatchersCount(REPOSITORY_WATCHER_TEST);
        ItemRepositoryViewModel itemrepositoryViewModel = new ItemRepositoryViewModel(repository, mockContext);
        assertEquals(repository.getWatchersCount().toString(), itemrepositoryViewModel.cantWatcher());
    }

    @Test public void shouldGetRepositoryLike() throws Exception {
        RepositoryItem repository = new RepositoryItem();
        repository.setStargazersCount(REPOSITORY_LIKE_TEST);
        ItemRepositoryViewModel itemrepositoryViewModel = new ItemRepositoryViewModel(repository, mockContext);
        assertEquals(repository.getStargazersCount().toString(), itemrepositoryViewModel.cantLike());
    }
}
