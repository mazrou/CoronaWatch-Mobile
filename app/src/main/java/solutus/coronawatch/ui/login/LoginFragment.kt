package solutus.coronawatch.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.coronawatch_mobile.R
import kotlinx.android.synthetic.main.login_fragment.*
import solutus.coronawatch.ui.activity.LoginActivity
import solutus.coronawatch.ui.activity.MainActivity
import solutus.coronawatch.ui.activity.MainActivity.Companion.replaceFragment

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        logout.setOnClickListener {
            replaceFragment(activity,R.id.login_fragment,RegisterFragment())
        }
        enter.setOnClickListener {
            if(user_name.text.toString()==""||password.text.toString()==""){
                Toast.makeText(activity,"يجب ملأ كل الحقول", Toast.LENGTH_SHORT).show()
            }else{
                //TODO :login
                val userName = user_name
                val password = password
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
            }
        }
        forgot_pw.setOnClickListener {
            //TODO : forget password
        }

    }

}
