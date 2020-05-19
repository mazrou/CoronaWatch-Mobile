package solutus.coronawatch.ui.loginActivity.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.coronawatch_mobile.R
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import solutus.coronawatch.data.entity.AppUser
import solutus.coronawatch.data.network.implementation.UserApi
import solutus.coronawatch.data.reposetory.implementation.UserRepository
import solutus.coronawatch.ui.mainActivity.MainActivity
import solutus.coronawatch.ui.mainActivity.MainActivity.Companion.replaceFragment


class LoginFragment : Fragment() {
    private val userRepository =
        UserRepository(UserApi.invoke())
    private var emailPassword = HashMap<String,String>()
    private lateinit var token : String
    private var user : AppUser? = null
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
            if(email.text.toString()==""||password.text.toString()==""){
                Toast.makeText(activity,"يجب ملأ كل الحقول", Toast.LENGTH_SHORT).show()
            }else{
                //TODO :login
                emailPassword["email"] = email.text.toString().trim()
                emailPassword["password"] = password.text.toString().trim()
                CoroutineScope(IO).launch{
                    apiRequest()
                }
            }
        }
        forgot_pw.setOnClickListener {
            //TODO : forget password
        }


    }

    private suspend fun apiRequest(){
        this.token = userRepository.loginUser(this.emailPassword)!!.token
        this.user = getUser(this.token)
        user?.let { showToken(it) }
    }
    private suspend fun showToken(user: AppUser){
        withContext(Dispatchers.Main){
            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra("token",token)
            intent.putExtra("user",user)
            startActivity(intent)
        }
    }
    private suspend fun getUser(token:String) : AppUser? {
        val appUser =  userRepository.getAuthAppUser("token "+token)
        return appUser
    }

}
