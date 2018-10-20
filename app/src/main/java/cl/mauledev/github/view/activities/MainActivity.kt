package cl.mauledev.github.view.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import androidx.navigation.Navigation
import cl.mauledev.github.R
import cl.mauledev.github.utils.ConnectionState
import cl.mauledev.github.utils.ConnectionUtils
import cl.mauledev.github.utils.Constants
import cl.mauledev.github.view.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity: AppCompatActivity() {

    private var viewModel: MainViewModel? = null

    private var snackbar: Snackbar? = null

    private var connectivityReceiver = ConnectivityStateBroadcastReceiver()

    private var callback = object: ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network?) {
            super.onAvailable(network)
            val intent = Intent()

            intent.action = Constants.CONNECTIVITY_ACTION
            sendBroadcast(intent)
        }

        override fun onLost(network: Network?) {
            super.onLost(network)
            val intent = Intent()

            intent.action = Constants.CONNECTIVITY_ACTION
            sendBroadcast(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViewModel()
        initSnackbar()
        initConnectionState()
    }

    override fun onStart() {
        super.onStart()
        registerConnectivityCallback()
    }

    private fun registerConnectivityCallback() {
        val filter = IntentFilter()
        filter.addAction(Constants.CONNECTIVITY_ACTION)
        registerReceiver(connectivityReceiver, filter)

        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        val builder = NetworkRequest.Builder()
        connectivityManager.registerNetworkCallback(builder.build(), callback)
    }

    override fun onStop() {
        super.onStop()
        unregisterConnectivityCallback()
    }

    private fun unregisterConnectivityCallback() {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(callback)

        unregisterReceiver(connectivityReceiver)
    }

    private fun initSnackbar() {
        snackbar = Snackbar.make(rootContainer, R.string.state_disconnected,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.state_action, {})
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private fun initConnectionState() {
        viewModel?.checkConnectedState()?.observe(this, Observer {
            when (it) {
                ConnectionState.CONNECTED -> {
                    snackbar?.dismiss()
                }
                ConnectionState.DISCONNECTED -> {
                    snackbar?.show()
                }
            }
        })
    }

    override fun onSupportNavigateUp() = Navigation
            .findNavController(this, R.id.nav_host).navigateUp()

    inner class ConnectivityStateBroadcastReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (!ConnectionUtils.isConnected(context))
                viewModel?.setConnectedState(ConnectionState.DISCONNECTED)
            else viewModel?.setConnectedState(ConnectionState.CONNECTED)
        }
    }
}
