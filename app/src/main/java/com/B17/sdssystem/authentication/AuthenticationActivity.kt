package com.B17.sdssystem.authentication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.B17.sdssystem.BuildConfig
import com.B17.sdssystem.R

class AuthenticationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)


        var fg : Fragment = LoginFragment()
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.auth_container, fg).addToBackStack(null)
            .commit();

    }
}
