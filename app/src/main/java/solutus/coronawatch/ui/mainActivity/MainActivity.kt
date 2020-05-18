package solutus.coronawatch.ui.mainActivity

import android.Manifest
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.coronawatch_mobile.R
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import solutus.coronawatch.data.db.entity.AppUser


class MainActivity : AppCompatActivity() {
    companion object{

        fun replaceFragment (activity: FragmentActivity?, layout:Int, fragment: Fragment){
                val fragmentTransaction : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
                fragmentTransaction?.replace(layout , fragment)
                fragmentTransaction?.addToBackStack(null)
                fragmentTransaction?.commit()
        }

    }
    private lateinit var navController:NavController
    private lateinit var toolbar: Toolbar
    lateinit var user : AppUser
    lateinit var token : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
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

   override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(null,navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater :MenuInflater = menuInflater
        inflater.inflate(R.menu.top_nav,menu)
        val switchOnOffItem = menu?.findItem(R.id.active_notifications)
        switchOnOffItem?.setActionView(R.layout.switch_layout)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {//A changer plus tard
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
