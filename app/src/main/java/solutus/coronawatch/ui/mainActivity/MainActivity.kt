package solutus.coronawatch.ui.mainActivity

import android.Manifest
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.coronawatch_mobile.R
import kotlinx.android.synthetic.main.activity_main.*
import solutus.coronawatch.data.entity.AppUser


class MainActivity : AppCompatActivity() {

    private lateinit var navController:NavController
    private lateinit var toolbar: Toolbar
    lateinit var user : AppUser
    lateinit var token : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA),
            1)
        user = intent.getSerializableExtra("user") as AppUser
        token = intent.getStringExtra("token")
        //set the tool bar and hide the return back home
        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        //set the buttom menu
        navController = Navigation.findNavController(this,R.id.nav_host_fragment)
        bottom_nav.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater :MenuInflater = menuInflater
        inflater.inflate(R.menu.popup_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {//A changer plus tard
        return when (item.itemId) {
            R.id.notification -> {
                Toast.makeText(this, "notifications", Toast.LENGTH_SHORT).show()
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
            R.id.active_notifications -> {
                if (item.isChecked) {
                    item.isChecked = false
                    Toast.makeText(this, "notification désactivées", Toast.LENGTH_SHORT).show()

                } else {
                    item.isChecked = true
                    Toast.makeText(this, "notification activées", Toast.LENGTH_SHORT).show()
                }
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
