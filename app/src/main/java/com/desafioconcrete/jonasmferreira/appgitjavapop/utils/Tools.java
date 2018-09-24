package com.desafioconcrete.jonasmferreira.appgitjavapop.utils;

import android.content.Context;
import android.content.Intent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {
    public static void goTo (Context lcontext, Class<?> lcls, boolean newTask){
        Intent intent = new Intent(lcontext, lcls);
        intent.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        lcontext.startActivity(intent);
    }


    public static String dateDB2BR(Date today){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dt = df.format(today);
        return dt;
    }
    public static String dateDB2BR(String dt){
        if(dt==null){
            return dt;
        }

        String dtFormat =  dt;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date date = df.parse(dt);
            dtFormat = dateDB2BR(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dtFormat;
    }

    public static Date dateBR2DB(String dt){
        Date date;

        if(dt==null){
            return new Date();
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = df.parse(dt);
            return date;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
