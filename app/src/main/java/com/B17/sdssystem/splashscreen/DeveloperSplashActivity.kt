package com.B17.sdssystem.splashscreen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import com.B17.sdssystem.R
import com.B17.sdssystem.authentication.LoginFragment

class DeveloperSplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer_splash)

        Handler().postDelayed(Runnable {

            var fg : Fragment = LoginFragment()
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.developerCon, fg).addToBackStack(null)
                .commit();
        }, 5000)

    }
}
