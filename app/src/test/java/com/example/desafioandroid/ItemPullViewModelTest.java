package com.example.desafioandroid;

import androidx.fragment.app.FragmentManager;
import com.example.desafioandroid.schema.PullItem;
import com.example.desafioandroid.schema.RepositoryItem;
import com.example.desafioandroid.viewModel.itemAdapter.ItemPullViewModel;
import com.example.desafioandroid.viewModel.itemAdapter.ItemRepositoryViewModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class) public class ItemPullViewModelTest {

    private static final String PULL_CREATEDAT_TEST = "2019-04-15T10:13:10Z";
    private static final String PULL_CREATEDAT_TRANSFORM_TEST = "15/04/2019 07:13";

    private static final int PULL_NUMBER_TEST = 872;
    private static final int REPOSITORY_LIKE_TEST = 4677;

    private FragmentManager mockContext = mock(FragmentManager.class);

    @Test
    public void shouldGetPullCreatedAt() {
        PullItem pullItem = new PullItem();
        pullItem.setCreatedAt(PULL_CREATEDAT_TEST);
        ItemPullViewModel itemPullViewModel = new ItemPullViewModel(pullItem);
        assertEquals(itemPullViewModel.pullDate(), PULL_CREATEDAT_TRANSFORM_TEST);
    }

    @Test public void shouldGetPullNumber() {
        PullItem pullItem = new PullItem();
        pullItem.setNumber(PULL_NUMBER_TEST);
        ItemPullViewModel itemrepositoryViewModel = new ItemPullViewModel(pullItem);
        assertEquals(pullItem.getNumber().toString(), itemrepositoryViewModel.pullState());
    }

    @Test public void shouldGetRepositoryLike() {
        RepositoryItem repository = new RepositoryItem();
        repository.setStargazersCount(REPOSITORY_LIKE_TEST);
        ItemRepositoryViewModel itemrepositoryViewModel = new ItemRepositoryViewModel(repository, mockContext);
        assertEquals(repository.getStargazersCount().toString(), itemrepositoryViewModel.cantLike());
    }
}
