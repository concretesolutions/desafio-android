package br.com.concretizando.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.List;

import br.com.concretizando.activity.DetailActivity;
import br.com.concretizando.activity.MainActivity;
import br.com.concretizando.model.ParameterIntent;

public class ChangePage {

    public void transferToDetail(Context ctx, List<ParameterIntent> parameters) {

        Intent intent = new Intent(ctx, DetailActivity.class);
        this.setIntentWithParameter(intent, parameters);
        ctx.startActivity(intent);
    }

    public void transferToMain(Context ctx) {

        Intent intent = new Intent(ctx, MainActivity.class);
        ctx.startActivity(intent);
    }

    private Intent setIntentWithParameter(Intent intent, List<ParameterIntent> parameters) {

        for(ParameterIntent parameter : parameters) {

            intent.putExtra(parameter.getKey(), parameter.getValue());
        }
        return intent;
    }

    public void openBrowserUrl(Context ctx, String url) {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        ctx.startActivity(intent);
    }
}









