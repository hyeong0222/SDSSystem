package com.B17.sdssystem.manager

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.B17.sdssystem.R
import com.B17.sdssystem.manager.assignment.employeelist.EmployeeListFragment
import com.B17.sdssystem.manager.project.ProjectFragment
import com.B17.sdssystem.manager.task.TaskFragment
import kotlinx.android.synthetic.main.activity_manager.*
import kotlinx.android.synthetic.main.app_bar_manager.*
import kotlinx.android.synthetic.main.content_manager.*

class ManagerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val sharedPrefFile : String = "MANAGER"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager)
        setSupportActionBar(toolbar)

        val taskFragment : Fragment = TaskFragment()
        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_managerActivity, taskFragment).addToBackStack(null).commit()

        val sharedPref: SharedPreferences = getSharedPreferences(sharedPrefFile, 0)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.manager, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.project_list -> {
                val fm : FragmentTransaction = supportFragmentManager.beginTransaction()
                val fg = ProjectFragment()

                fm.replace(R.id.fl_managerActivity, fg)
                fm.addToBackStack(null)
                fm.commit()
            }
            R.id.employeeList -> {
                val fm : FragmentTransaction = supportFragmentManager.beginTransaction()
                val fg = EmployeeListFragment()

                fm.replace(R.id.fl_managerActivity, fg)
                fm.addToBackStack(null)
                fm.commit()
            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
