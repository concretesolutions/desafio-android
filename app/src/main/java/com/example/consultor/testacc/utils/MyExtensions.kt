package com.example.consultor.testacc.utils

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import java.text.SimpleDateFormat


fun Fragment.startCommit(activity: AppCompatActivity, container: FrameLayout) {

    activity.supportFragmentManager.beginTransaction().replace(container.id,this).commit()

}
 fun String.giveCustomFormat(): String {


     val format1 =  SimpleDateFormat("yyyy-MM-dd")
     val format2 =  SimpleDateFormat("dd-MM-yyyy")
     val date = format1.parse(this.substring(0,indexOf("T")))

     return format2.format(date)

}