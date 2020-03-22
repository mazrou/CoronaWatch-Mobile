package com.example.coronawtch_mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.coronawatch_mobile.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import solutus.coronawatch.ui.health.HealthFragment
import solutus.coronawatch.ui.home.HomeFragment
import solutus.coronawatch.ui.info.InfoFragment
import solutus.coronawatch.ui.map.MapFragment
import solutus.coronawatch.ui.user.UserFragment

class MainActivity : AppCompatActivity() {

    private lateinit var navController:NavController


  private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
      item -> when(item.itemId){
            R.id.healthFragment ->{
                replaceFragment(HealthFragment())
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
        setSupportActionBar(toolbar)
        replaceFragment(HomeFragment())
        bottom_nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

    private fun replaceFragment (fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment , fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


}
