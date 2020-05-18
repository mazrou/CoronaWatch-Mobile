package solutus.coronawatch.ui.firstActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coronawatch_mobile.R
import solutus.coronawatch.ui.mainActivity.MainActivity.Companion.replaceFragment
import solutus.coronawatch.ui.firstActivity.firstView.StartFragment

class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        replaceFragment(this,R.id.first_fragment,StartFragment())
    }
}
