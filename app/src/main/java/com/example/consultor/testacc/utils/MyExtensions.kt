package com.example.consultor.testacc.utils

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import java.text.SimpleDateFormat


fun Fragment.startCommit(activity: AppCompatActivity, container: FrameLayout) {

    activity.supportFragmentManager.beginTransaction().replace(container.id,this).commit()

}

// cambia el formato de la fecha de creacion
 fun String.giveCustomFormat(): String {


     val format1 =  SimpleDateFormat("yyyy-MM-dd")
     val format2 =  SimpleDateFormat("dd MMM yyyy")
     val date = format1.parse(this.substring(0,indexOf("T")))

     return format2.format(date)

}
//valida que tenga contenido si no coloca texto por defecto
fun String.setvalidText(): String? {


    return if(!this.isBlank()) this else "No description"
}