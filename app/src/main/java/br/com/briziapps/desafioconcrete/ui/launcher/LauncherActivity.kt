package br.com.briziapps.desafioconcrete.ui.launcher

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import br.com.briziapps.desafioconcrete.R
import br.com.briziapps.desafioconcrete.ui.main.MainActivity

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        //init main activity
        initMain()
    }

    private fun initMain(){
        // use handler for delay start activity to show logo image
        val mHandler = Handler()
        val mRunnable = Runnable {
            val intentStartMain = Intent(this, MainActivity::class.java)
            startActivity(intentStartMain)
            finish()
        }
        mHandler.postDelayed(mRunnable, 800)
    }
}
