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
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

class AuthenticationActivity : AppCompatActivity() {

    //adding comments about the shared activity
    //new commits
    //two new commits



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fabric.with(this, Crashlytics())
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
