package solutus.coronawatch.context

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import solutus.coronawatch.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*button.setOnClickListener{
            val intent = Intent(this, SecondMainActivity::class.java)
            startActivity(intent)
        }*/
    }

}
