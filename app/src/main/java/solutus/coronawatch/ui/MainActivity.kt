package com.example.coronawtch_mobile

import android.os.Bundle
import android.text.Layout
import android.text.SpannableString
import android.text.style.AlignmentSpan
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.coronawatch_mobile.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var navController:NavController
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.app_name)
        supportActionBar?.setLogo(R.mipmap.ic_logo_corona_blanc)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        bottom_nav.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this,navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(null,navController)
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
