package com.B17.sdssystem.developer.developertask

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.B17.sdssystem.R

class DevTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dev_test)

        var fg : Fragment = TaskListFragment()
       supportFragmentManager.beginTransaction().replace(R.id.dev_act_container,fg).commit()
    }
}
