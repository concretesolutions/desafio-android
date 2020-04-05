package com.igormeira.githubpop.handler;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import com.igormeira.githubpop.R;

public class Dialog {

    public static ProgressDialog showLoadingDialog(Context ctx) {
        ProgressDialog fpDialog = ProgressDialog.show(ctx, "",
                "", true);

        fpDialog.setContentView(R.layout.item_loading);
        fpDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        return fpDialog;
    }

}
