package solutus.coronawatch.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.coronawatch_mobile.R
import kotlinx.android.synthetic.main.register_fragment.*
import solutus.coronawatch.ui.activity.MainActivity

class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        login.setOnClickListener {
            MainActivity.replaceFragment(activity, R.id.login_fragment, LoginFragment())
        }

        register.setOnClickListener {
            if (user_name.text.toString() == "" || first_name.text.toString() == "" || second_name.text.toString() == "" || email.text.toString() == "" || password.text.toString() == "" || confirm_password.text.toString() == "") {
                   Toast.makeText(activity,"يجب ملأ كل الحقول",Toast.LENGTH_SHORT).show()
            } else {
                if (agree.isChecked) {
                    //TODO:Registre un user
                    //val user = User()
                    val userName = user_name.text
                    val fName = first_name.text
                    val sName = second_name.text
                    val email = email.text
                    val firstPassword = password.text
                    val secondPassword = confirm_password.text
                    val intent = Intent(activity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(activity, "يجب ان توافق على شروط الاستخدام", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        condition.setOnClickListener {
            //TODO: afficher les condition d'utlisation
        }
    }

}
