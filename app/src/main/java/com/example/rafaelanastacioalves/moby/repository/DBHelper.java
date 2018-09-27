package com.example.rafaelanastacioalves.moby.repository;

import com.example.rafaelanastacioalves.moby.database.AppDatabase;
import com.example.rafaelanastacioalves.moby.vo.Pull;
import com.example.rafaelanastacioalves.moby.vo.Pull_Table;
import com.example.rafaelanastacioalves.moby.vo.Repo;
import com.example.rafaelanastacioalves.moby.vo.RepoContainer;
import com.example.rafaelanastacioalves.moby.vo.Repo_Table;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.OrderBy;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    public static RepoContainer getRepoContainerOfPage(String i) {
        List<Repo> listRepo = SQLite.select()
                .from(Repo.class)
                .where(Repo_Table.page.eq(i))
                .queryList();


        if (listRepo == null || listRepo.isEmpty()) {
            return null;
        }

        return new RepoContainer(listRepo);

    }

    public static void setRepoContainerOfPage(String page, RepoContainer container) {
        FlowManager.getDatabase(AppDatabase.class).executeTransaction(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                List<Repo> listRepo = container.getRepoList();

                for (Repo repo : listRepo) {
                    repo.setPage(page);
                    repo.save();
                }
            }
        });
    }

    public static void setPullListForRepo(String creator, String repoName, ArrayList<Pull> pullList) {

        for (int i =0; i < pullList.size(); i++) {
            Pull pull = pullList.get(i);
            pull.setOrder(i);
            pull.setCreator(creator);
            pull.setRepoName(repoName);
            pull.save();

        }



    }


    public static ArrayList<Pull> getPullListForRepo(String creator, String repo) {

        List<Pull> listPull = SQLite.select()
                .from(Pull.class)
                .where(Pull_Table.creator.eq(creator), Pull_Table.repoName.eq(repo))
                .orderBy(OrderBy.fromProperty(Pull_Table.order).ascending())
                .queryList();

        if (listPull == null || listPull.isEmpty()) {
            return null;
        }

        return  (ArrayList<Pull>) listPull;
    }

}
