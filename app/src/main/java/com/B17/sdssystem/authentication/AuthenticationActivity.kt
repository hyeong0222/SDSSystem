package com.B17.sdssystem.authentication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.B17.sdssystem.BuildConfig
import com.B17.sdssystem.R
import com.B17.sdssystem.splashscreen.DeveloperSplashActivity
import com.B17.sdssystem.splashscreen.ManagerSplashActivity
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop

class AuthenticationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        if (BuildConfig.FLAVOR.equals("manager")) {

             /*startActivity(intentFor<ManagerSplashActivity>())*/
             var myIntent = Intent(this, ManagerSplashActivity::class.java)
             startActivity(myIntent)
        } else {
            var myIntent = Intent(this, DeveloperSplashActivity::class.java)
            startActivity(myIntent)
        }


    }
}
