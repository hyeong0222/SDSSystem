package com.B17.sdssystem

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.B17.sdssystem.project.ProjectFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fg = ProjectFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fg)
            .addToBackStack(null).commit()
    }
}
