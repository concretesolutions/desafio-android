package com.silvioapps.githubkotlin.features.list.activities

import com.silvioapps.githubkotlin.features.shared.views.activities.CustomActivity
import android.os.Bundle
import com.google.android.gms.security.ProviderInstaller
import com.silvioapps.githubkotlin.R
import com.silvioapps.githubkotlin.features.list.fragments.MainFragment
import javax.net.ssl.SSLContext

class MainActivity : CustomActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            attachFragment(R.id.frameLayout, MainFragment())
        }

        try {
            ProviderInstaller.installIfNeeded(getApplicationContext())
            val sslContext = SSLContext.getInstance("TLSv1.2")
            sslContext.init(null, null, null)
            sslContext.createSSLEngine()
        }
        catch(e : Exception){
            e.printStackTrace()
        }
    }
}
