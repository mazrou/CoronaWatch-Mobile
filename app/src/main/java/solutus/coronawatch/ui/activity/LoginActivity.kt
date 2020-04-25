package solutus.coronawatch.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coronawatch_mobile.R
import solutus.coronawatch.ui.activity.MainActivity.Companion.replaceFragment
import solutus.coronawatch.ui.login.LoginFragment

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        replaceFragment(this,R.id.login_fragment,LoginFragment())
    }
}
