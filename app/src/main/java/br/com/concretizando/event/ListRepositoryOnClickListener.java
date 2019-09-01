package br.com.concretizando.event;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.concretizando.constant.ParameterIntentConstant;
import br.com.concretizando.model.ParameterIntent;
import br.com.concretizando.model.Repository;
import br.com.concretizando.util.ChangePage;

public class ListRepositoryOnClickListener implements View.OnClickListener {

    private final Context ctx;
    private final List<Repository> repos;
    private final RecyclerView list;

    public ListRepositoryOnClickListener(Context ctx, List<Repository> repos,
                                         RecyclerView list) {

        this.ctx = ctx;
        this.repos = repos;
        this.list = list;
    }

    @Override
    public void onClick(View view) {

        Repository repo = repos.get(list.getChildLayoutPosition(view));
        ChangePage changePage = new ChangePage();
        List<ParameterIntent> parameters = new ArrayList<>();
        parameters.add(new ParameterIntent(ParameterIntentConstant.PARAM_1, repo.getOwner()
                .getLogin()));
        parameters.add(new ParameterIntent(ParameterIntentConstant.PARAM_2, repo.getName()));
        changePage.transferToDetail(ctx, parameters);
    }
}
