package com.B17.sdssystem.splashscreen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.B17.sdssystem.R
import android.content.Intent
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.os.HandlerCompat.postDelayed
import com.B17.sdssystem.authentication.LoginFragment


class ManagerSplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.B17.sdssystem.R.layout.activity_manager_splash)

        Handler().postDelayed(Runnable {

            var fg : Fragment = LoginFragment()
            getSupportFragmentManager().beginTransaction()
                .add(R.id.managerCon, fg).addToBackStack(null)
                .commit();

        }, 5000)

    }
}
