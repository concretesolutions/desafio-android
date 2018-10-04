package com.br.apigithub.utils;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ronan.lima on 19/07/17.
 */

public class PermissionUtils {

    /**
     * Verifica se o usuário já concedeu permissão para a funcionalidade solicitada.
     *
     * @param activity
     * @param requestCode
     * @param permissions
     * @return
     */
    public static boolean validate(AppCompatActivity activity, int requestCode, String... permissions) {
        List<String> list = justCheckPermission(activity, permissions);
        if (list.isEmpty()) {
            return true;
        }
        requestPermissions(activity, requestCode, list);
        return false;
    }

    /**
     * @param activity
     * @param requestCode
     * @param list
     */
    public static void requestPermissions(AppCompatActivity activity, int requestCode, List<String> list) {
        String[] newPermissions = new String[list.size()];
        list.toArray(newPermissions);
        ActivityCompat.requestPermissions(activity, newPermissions, requestCode);
    }

    /**
     * Apenas verifica se a permissão solicitada foi concedida, sem pedir acesso à ela caso a lista
     * retornada seja diferente de vazia.
     *
     * @param activity
     * @param permissions
     * @return
     */
    public static List<String> justCheckPermission(AppCompatActivity activity, String... permissions) {
        List<String> list = new ArrayList<>();
        for (String perm : permissions) {
            boolean ok = ContextCompat.checkSelfPermission(activity, perm) == PackageManager.PERMISSION_GRANTED;
            if (!ok) {
                list.add(perm);
            }
        }
        return list;
    }

    /**
     * Verifica se o usuário concedeu a permissão solicitada.
     *
     * @param permissoes
     * @return
     */
    public static boolean isPermissaoConcedida(int[] permissoes) {
        boolean isPermissaoConcedida = false;

        for (int i = 0; i < permissoes.length; i++) {
            if (permissoes[i] > PackageManager.PERMISSION_DENIED) {
                isPermissaoConcedida = true;
            } else {
                isPermissaoConcedida = false;
            }
        }
        return isPermissaoConcedida;
    }

}
