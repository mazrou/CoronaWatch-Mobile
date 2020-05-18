package solutus.coronawatch.ui.loginActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coronawatch_mobile.R
import solutus.coronawatch.ui.mainActivity.MainActivity.Companion.replaceFragment
import solutus.coronawatch.ui.loginActivity.login.LoginFragment

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        replaceFragment(this,R.id.login_fragment,LoginFragment())
    }
}
