package com.B17.sdssystem.manager.subtask

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.B17.sdssystem.R

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        var fg : Fragment = SubtaskFragment()
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.testContainer, fg)
            .commit();

    }
}
