package com.example.coronawtch_mobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.coronawatch_mobile.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import solutus.coronawatch.ui.health.healthFragment
import solutus.coronawatch.ui.home.homeFragment
import solutus.coronawatch.ui.info.infoFragment
import solutus.coronawatch.ui.map.mapFragment
import solutus.coronawatch.ui.user.userFragment

class MainActivity : AppCompatActivity() {
    private lateinit var navController:NavController
    private lateinit var toolbar: Toolbar
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
            item -> when(item.itemId){
        R.id.healthFragment ->{
           replaceFragment(healthFragment)
            return@OnNavigationItemSelectedListener true
        }
        R.id.mapFragment ->{
            replaceFragment(MapFragment())
            return@OnNavigationItemSelectedListener true
        }
        R.id.userFragment ->{
            replaceFragment(UserFragment())
            return@OnNavigationItemSelectedListener true
        }
        R.id.infoFragment ->{
            replaceFragment(InfoFragment())
            return@OnNavigationItemSelectedListener true
        }
        R.id.homeFragment ->{
            replaceFragment(HomeFragment())
            return@OnNavigationItemSelectedListener true
        }
    }
        return@OnNavigationItemSelectedListener false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.app_name)
        supportActionBar?.setLogo(R.mipmap.ic_logo_corona_blanc)

        bottom_nav.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this,navController) */
        replaceFragment(HomeFragment())
        bottom_nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun replaceFragment (fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment , fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater :MenuInflater = menuInflater
        inflater.inflate(R.menu.top_nav,menu)
        val switchOnOffItem = menu?.findItem(R.id.active_notifications)
        switchOnOffItem?.setActionView(R.layout.switch_layout)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.notification_item -> {
                Toast.makeText(this, "notification", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.settings -> {
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.help -> {
                Toast.makeText(this, "help", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.logout -> {
                Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


}
