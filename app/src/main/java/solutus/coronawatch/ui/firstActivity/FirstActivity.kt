package solutus.coronawatch.ui.firstActivity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coronawatch_mobile.R
import com.facebook.AccessToken





class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired

        if (isLoggedIn){
            Toast.makeText(this , "mchhgjhkghjgohjga" , Toast.LENGTH_LONG).show()
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
    }
}
