package com.github.api.morepopulargithubapp.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.github.api.morepopulargithubapp.R;


public class ViewUtil {
    public static AlertDialog.Builder dialogo(Context context, String mensagem) {
        return new AlertDialog.Builder(context, R.style.Theme_AppCompat_Light_Dialog_Alert)
                .setMessage( mensagem );
    }

    public static void alert(Context context, String mensagem){
        alert(context, mensagem, null);
    }

    public static void alert(Context context, String mensagem, DialogInterface.OnDismissListener listener) {
        dialogo(context, mensagem)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }


}
