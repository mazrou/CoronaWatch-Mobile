package solutus.coronawatch.ui.mainActivity

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.coronawatch_mobile.R
import kotlinx.android.synthetic.main.activity_main.*
import solutus.coronawatch.data.entity.AppUser
import solutus.coronawatch.ui.loginActivity.LoginActivity


class MainActivity : AppCompatActivity() {

    companion object {
        var isLoginLiveData = MutableLiveData<Boolean>(false)
    }

    private lateinit var navController:NavController
    private lateinit var toolbar: Toolbar
    var user: AppUser? = null
    var token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //request for permission
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            1)

        //set the tool bar and hide the return back home
        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //set the bottom menu
        navController = Navigation.findNavController(this,R.id.nav_host_fragment)
        bottom_nav.setupWithNavController(navController)

        isLoginLiveData.observe(this, Observer { isLogin ->
            if (isLogin) {
                user = intent.getSerializableExtra("user") as AppUser
                token = intent.getStringExtra("token")
                //change bottom menu
                bottom_nav.menu.clear()
                bottom_nav.inflateMenu(R.menu.user_bottom_nav)
                nav_host_fragment.findNavController().setGraph(R.navigation.mobile_navigation)

            }else{
                bottom_nav.menu.clear()
                bottom_nav.inflateMenu(R.menu.visitor_bottom_nav)
                nav_host_fragment.findNavController().setGraph(R.navigation.visitor_mobile_navigation)
            }
        })



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater :MenuInflater = menuInflater
        inflater.inflate(R.menu.popup_menu, menu)

        val loginItem = menu!!.findItem(R.id.login)
        isLoginLiveData.observe(this, Observer { isLogin ->
            if (isLogin) {
                loginItem.isVisible = false
                menu.setGroupVisible(R.id.user_group, true)
            } else {
                loginItem.isVisible = true
                menu.setGroupVisible(R.id.user_group, false)
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.login -> {
                showDialog()
                true
            }

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
                isLoginLiveData.value = false
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custome_login_dialog)
        val positiveButton = dialog.findViewById(R.id.positive_button) as Button
        val negativeButton = dialog.findViewById(R.id.negative_button) as Button
        positiveButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            dialog.dismiss()
        }
        negativeButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }





}
