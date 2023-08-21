package com.firstapplication.mars.ui.activity

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.firstapplication.mars.databinding.ActivityMainBinding
import com.firstapplication.mars.ui.receiver.NetworkStateReceiver
import com.firstapplication.mars.ui.utils.NetworkConnection
import com.firstapplication.mars.ui.utils.NetworkStateObservable
import com.firstapplication.mars.ui.utils.NetworkStateObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NetworkStateObserver {

    private lateinit var binding: ActivityMainBinding

    private lateinit var broadcastReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        registerReceiver()
        NetworkStateObservable.observe(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
        NetworkStateObservable.unregister(this)
    }

    override fun onChanged(value: NetworkConnection) {
        when (value) {
            NetworkConnection.Connected -> {
                setAlphaAnimation(binding.noConnectionLayout, 0.0f)
                setAlphaAnimation(binding.navContainer, 1.0f)
            }

            NetworkConnection.Disconnected -> {
                binding.noConnectionLayout.alpha = 1.0f
                setAlphaAnimation(binding.noConnectionLayout, 1.0f)
                setAlphaAnimation(binding.navContainer, 0.0f)
            }
        }
    }

    private fun setAlphaAnimation(view: View, alpha: Float) {
        view.animate().setDuration(200L).alpha(alpha)
    }

    private fun registerReceiver() {
        broadcastReceiver = NetworkStateReceiver()

        @Suppress("DEPRECATION") val intentFilter = IntentFilter().also {
            it.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        }
        registerReceiver(broadcastReceiver, intentFilter)
    }
}