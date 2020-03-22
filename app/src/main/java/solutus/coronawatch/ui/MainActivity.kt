package com.example.coronawtch_mobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.coronawatch_mobile.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import solutus.coronawatch.ui.health.HealthFragment
import solutus.coronawatch.ui.home.HomeFragment
import solutus.coronawatch.ui.info.InfoFragment
import solutus.coronawatch.ui.map.MapFragment
import solutus.coronawatch.ui.user.UserFragment

class MainActivity : AppCompatActivity() {

    // Decladring the ToolBar
    private lateinit var toolbar: Toolbar
  
    // Declaring the on Navigation Item Selected Listener for the Bottom Navigation View
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
      
      // set the toolbar and her options
        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        
      // put the Home Fragment as default
        replaceFragment(HomeFragment())
      
      // set the OnNavigationItemSelectedListener to the bottom_nav ( the ID of the Bottom navigation view ) 
        bottom_nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
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
 /*
 * This methode make us replacting the fragment on the nav_host_fragment
 */
   private fun replaceFragment (fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment , fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}
