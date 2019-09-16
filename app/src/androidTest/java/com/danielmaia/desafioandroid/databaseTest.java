package com.danielmaia.desafioandroid;

import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.danielmaia.desafioandroid.dao.PullDao;
import com.danielmaia.desafioandroid.dao.RepoDao;
import com.danielmaia.desafioandroid.database.RepoDatabase;
import com.danielmaia.desafioandroid.model.Pull;
import com.danielmaia.desafioandroid.model.Repo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class databaseTest {

    private RepoDatabase db;

    @Before
    public void createDb() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(), RepoDatabase.class).build();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void insertAndDeleteRepoTest() {
        Repo repo = new Repo();

        repo.setId(1);
        repo.setName("repository test");
        repo.setDescription("description test");
        repo.setStargazers_count(10);
        repo.setForks(10);
        repo.setOwnerId(1);
        repo.setOwner("Daniel");
        repo.setAvatar("https://avatars3.githubusercontent.com/u/36260787?v=4");

        RepoDao repoDao = db.getRepoDao();
        repoDao.insert(repo);

        List<Repo> r = repoDao.getById(1);
        Assert.assertEquals(r.get(0).getId(), repo.getId());

        repoDao.deleteById(repo.getId());
        r = repoDao.getById(1);
        Assert.assertEquals(r.size(), 0);
    }

    @Test
    public void insertAndDeletePullTest() {
        Pull pull = new Pull();

        pull.setId(1);
        pull.setRepo("repository test");
        pull.setTitle("pull test");
        pull.setBody("body test");
        pull.setState("open");
        pull.setUrl("https://avatars3.githubusercontent.com/u/36260787?v=4");
        pull.setAvatar("https://avatars3.githubusercontent.com/u/36260787?v=4");
        pull.setOwner("Daniel");

        PullDao pullDao = db.getPullDao();
        pullDao.insert(pull);

        List<Pull> p = pullDao.getById(1);
        Assert.assertEquals(p.get(0).getId(), pull.getId());

        pullDao.deleteById(pull.getId());
        p = pullDao.getById(1);
        Assert.assertEquals(p.size(), 0);
    }
}








